package br.com.ifeira.entrega.controller;

import br.com.ifeira.compra.shared.dao.PedidoDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.dao.PessoaDAO;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.compra.shared.enums.StatusPedido;
import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.entrega.config.MailingConfig;
import br.com.ifeira.entrega.dao.EntregaDAO;
import br.com.ifeira.entrega.dao.EntregadorDAO;
import br.com.ifeira.entrega.entity.Entrega;
import br.com.ifeira.entrega.entity.Entregador;
import br.com.ifeira.entrega.enums.StatusEntrega;
import br.com.ifeira.entrega.factories.EntregaConcretFactory;
import br.com.ifeira.entrega.factories.EntregaFactory;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;


@Component
public class EntregaController {

    @Autowired
    private MailingConfig mailingConfig;
    private Notificavel notificador;
    private Persistivel<Entrega, Long> entregaDAO;
    private Persistivel<Pedido, Long> pedidoDAO;
    private Persistivel<Entregador, String> entregadorDAO;
    private Persistivel<Pessoa, String> pessoaDAO;
    private EntregaFactory entregaFactory;
    private Map<String, Queue<Entregador>> entregadorFeira;
    private JdbcTemplate jdbcTemplate;

    public EntregaController(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.entregaDAO = new EntregaDAO(jdbcTemplate);
        this.pedidoDAO = new PedidoDAO(jdbcTemplate);
        this.pessoaDAO = new PessoaDAO(jdbcTemplate);
        this.entregadorDAO = new EntregadorDAO(jdbcTemplate);
        this.entregaFactory = new EntregaConcretFactory();
        this.entregadorFeira = new HashMap<>();
    }

    @Transactional
    public Entrega realizarEntrega(Long idEntrega) {
        Entrega entrega = this.entregaDAO.buscar(idEntrega);
        entrega.marcarRealizada();
        entrega.setDataRealizacao(new Date());

        Entrega entregaSalva = this.entregaDAO.editar(entrega);

        Pedido pedido = entregaSalva.getPedido();
        pedido.marcarEntregue();
        pedido.setDataEntrega(entregaSalva.getDataRealizacao());

        this.pedidoDAO.editar(pedido);

        String mensagem = "Pedido numero: " + entregaSalva.getPedido().getNumeroPedido() + " STATUS: " + pedido.getStatusPedido().name();
        String assunto = "Status Pedido " + entrega.getPedido().getNumeroPedido();
        atualizarSaldo(entregaSalva);
        notificar(entregaSalva, pedido.getCliente().getEmail(), mensagem, assunto);
        return entregaSalva;
    }

    public List<Entrega> buscarEntregas(Principal principal, String statusEntrega) {
        StatusEntrega parsedStatusEntrega = statusEntrega != null ? StatusEntrega.valueOf(statusEntrega) : null;
        Entregador entregador = this.entregadorDAO.buscar(principal.getName());
        Object[] parametros = new Object[2];
        parametros[0] = parsedStatusEntrega;
        parametros[1] = entregador;
        return this.entregaDAO.buscarComParametros(parametros);
    }

    public Entregador selecionarEntregador(String contexto) {
        if(this.entregadorFeira.get(contexto) == null) {
            Object[] parametros = new Object[1];
            parametros[0] = contexto;
            List<Entregador> entregadores = this.entregadorDAO.buscarComParametros(parametros);
            Queue<Entregador> entregadorQueue = new LinkedList<>(entregadores);
            this.entregadorFeira.put(contexto, entregadorQueue);
        }
        Queue<Entregador> entregadorQueue = this.entregadorFeira.get(contexto);
        Entregador entregador = entregadorQueue.remove();
        entregadorQueue.add(entregador);
        return entregador;
    }

    public Entrega gerarEntrega(PagamentoDTO pagamento) {
        Pedido pedido = this.pedidoDAO.buscar(pagamento.getNumeroPedido());
        if(pedido == null) throw new AmqpRejectAndDontRequeueException("REJEITADO");
        try {
            String contexto = pedido.getCarrinho()
                    .getListaProdutoQuantidade()
                    .get(0)
                    .getProdutoFeira()
                    .getFeira()
                    .getContext();
            Entregador entregador = selecionarEntregador(contexto);

            if(entregador == null || !pedido.getStatusPedido().equals(StatusPedido.PAGO)) throw new AmqpRejectAndDontRequeueException("REJEITADO");

            Entrega entrega = this.entregaFactory.criarEntrega(pedido, entregador);
            Entrega entregaRetornada = this.entregaDAO.salvar(entrega);
            String mensagem = "Entrega referente ao pedido " + pedido.getNumeroPedido() + " na data: " + pedido.getDataEntrega();
            String assunto = "Entrega referente ao pedido " + pedido.getNumeroPedido();
            notificar(entregaRetornada, entregaRetornada.getEntregador().getEmail(), mensagem, assunto);
            return entregaRetornada;
        }catch (AmqpRejectAndDontRequeueException e) {
            pedido.marcarCancelado();
            this.pedidoDAO.editar(pedido);
            throw e;
        }

    }

    public void notificar(Entrega entrega, String email, String mensagem, String assunto) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.mailingConfig.AUTH);
        properties.put("mail.smtp.starttls.enable", this.mailingConfig.STARTTLS);
        properties.put("mail.smtp.host", this.mailingConfig.HOST);
        properties.put("mail.smtp.port", this.mailingConfig.PORT);

        notificador = new NotificacaoEmail(properties, this.mailingConfig.ACCOUNT, this.mailingConfig.PASSWORD);

        this.notificador.enviarNotificacao(mensagem, email, assunto);
    }

    public void atualizarSaldo(Entrega entrega) {
        Pedido pedido = entrega.getPedido();

        String contextoFeira = pedido.getCarrinho().getListaProdutoQuantidade().get(0).getProdutoFeira().getFeira().getContext();

        Double valorTotal = pedido.getValorTotal();

        Double pagFeirante = valorTotal * 0.85;
        Double pagEntregador = valorTotal * 0.05;

        this.jdbcTemplate.update(
                "INSERT INTO SALDO_ENTREGADOR\n" +
                        "(NUMERO_PEDIDO, CPF_ENTREGADOR, VALOR_TOTAL)\n" +
                        "VALUES(?, ?, ?);\n", pedido.getNumeroPedido(), entrega.getEntregador().getCpf(), pagEntregador);
        this.jdbcTemplate.update(
                "INSERT INTO SALDO_FEIRANTE\n" +
                        "(NUMERO_PEDIDO, ID_FEIRA, VALOR_TOTAL)\n" +
                        "VALUES(?, ?, ?);\n", pedido.getNumeroPedido(), contextoFeira, pagFeirante);
    }

    @Scheduled(cron = "0 0 1 * * SAT")
    public void processarEntregasAtrasadas() {
        EntregaDAO parsedEntregaDAO = (EntregaDAO) this.entregaDAO;
        List<Entrega> entregasAtrasadas = parsedEntregaDAO.buscarEntregasAtrasadas();
        entregasAtrasadas.forEach(entrega -> {
            Pedido pedido = this.pedidoDAO.buscar(entrega.getPedido().getNumeroPedido());
            entrega.marcarCancelada();
            pedido.marcarCancelado();
            this.entregaDAO.editar(entrega);
            this.pedidoDAO.editar(pedido);
            String mensagem = "Pedido " + pedido.getNumeroPedido() + " CANCELADO na data: " + new Date() + " devido ao ATRASO na entrega.";
            String assunto = "Entrega referente ao pedido " + pedido.getNumeroPedido();
        });
    }

}
