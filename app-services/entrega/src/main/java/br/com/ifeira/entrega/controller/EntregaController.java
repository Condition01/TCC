package br.com.ifeira.entrega.controller;

import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.dao.PersistivelContextual;
import br.com.ifeira.compra.shared.dao.PessoaDAO;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.entrega.config.MailingConfig;
import br.com.ifeira.entrega.dao.EntregaDAO;
import br.com.ifeira.entrega.dao.EntregadorDAO;
import br.com.ifeira.entrega.entity.Entrega;
import br.com.ifeira.entrega.entity.Entregador;
import br.com.ifeira.entrega.factories.EntregaConcretFactory;
import br.com.ifeira.entrega.factories.EntregaFactory;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.*;


@Component
public class EntregaController {

    @Autowired
    private MailingConfig mailingConfig;
    private Notificavel notificador;
    private Persistivel<Entrega, Long> entregaDAO;
    private Persistivel<Pedido, Long> pedidoDAO;
    private PersistivelContextual<Entregador, Long> entregadorDAO;
    private Persistivel<Pessoa, String> pessoaDAO;
    private EntregaFactory entregaFactory;
    private Map<String, Queue<Entregador>> entregadorFeira;

    public EntregaController(@Autowired JdbcTemplate jdbcTemplate) {
        this.entregaDAO = new EntregaDAO(jdbcTemplate);
        this.pessoaDAO = new PessoaDAO(jdbcTemplate);
        this.entregadorDAO = new EntregadorDAO(jdbcTemplate);
        this.entregaFactory = new EntregaConcretFactory();
    }

    public Entrega realizarEntrega(Long numeroEntrega) {
        Entrega entrega = this.entregaDAO.buscar(numeroEntrega);
        entrega.marcarRealizada();
        return this.entregaDAO.editar(entrega);
    }

    public List<Entrega> buscarEntregas(Principal principal) {
        Pessoa pessoa = this.pessoaDAO.buscar(principal.getName());

        return null;
    }

    public Entregador selecionarEntregador(String contexto) {
        if(this.entregadorFeira.get(contexto) != null) {
            List<Entregador> entregadores = this.entregadorDAO.listar(contexto);
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
        String contexto = pedido.getCarrinho()
                .getListaProdutoQuantidade()
                .get(0)
                .getProdutoFeira()
                .getFeira()
                .getContext();
        Pessoa entregador = selecionarEntregador(contexto);
        Entrega entrega = this.entregaFactory.criarEntrega(pedido, entregador);
        return null;
    }

    public void notificar(PagamentoDTO pagamento, String email, String mensagem) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.mailingConfig.AUTH);
        properties.put("mail.smtp.starttls.enable", this.mailingConfig.STARTTLS);
        properties.put("mail.smtp.host", this.mailingConfig.HOST);
        properties.put("mail.smtp.port", this.mailingConfig.PORT);

        notificador = new NotificacaoEmail(properties, this.mailingConfig.ACCOUNT, this.mailingConfig.PASSWORD);

        this.notificador.enviarNotificacao(mensagem, email, "Status Pedido " + pagamento.getNumeroPedido());
    }

    public boolean atualizarSaldo() {
        return false;
    }

}
