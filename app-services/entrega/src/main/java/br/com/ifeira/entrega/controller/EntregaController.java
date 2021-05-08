package br.com.ifeira.entrega.controller;

import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.dao.PessoaDAO;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.entrega.config.MailingConfig;
import br.com.ifeira.entrega.dao.EntregaDAO;
import br.com.ifeira.entrega.entity.Entrega;
import br.com.ifeira.entrega.factory.EntregaSimplesFactory;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class EntregaController {

    @Autowired
    private MailingConfig mailingConfig;
    private Notificavel notificador;
    private Persistivel<Entrega, Long> entregaDAO;
    private Persistivel<Pedido, Long> pedidoDAO;
    private Persistivel<Pessoa, String> pessoaDAO;

    public EntregaController(@Autowired JdbcTemplate jdbcTemplate) {
        this.entregaDAO = new EntregaDAO(jdbcTemplate);
        this.pessoaDAO = new PessoaDAO(jdbcTemplate);
        this.pessoaDAO = new PessoaDAO(jdbcTemplate);
    }

    public EntregaController() {
    }

    public Entrega realizarEntrega(Entrega entrega) {
        return null;
    }

    public EntregaSimplesFactory buscarEntregas(EntregaSimplesFactory entregaSimplesFactory) {
        return null;
    }

    public Pessoa selecionarEntregador(Pessoa entregador) {
        return null;
    }

    public Entrega gerarEntrega(PagamentoDTO pagamoentoDTO) {
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
