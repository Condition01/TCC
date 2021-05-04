package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.dao.PagamentoDAO;
import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.entity.PagamentoProdutor;
import br.com.ifeira.compra.shared.dao.CarrinhoDAO;
import br.com.ifeira.compra.shared.dao.PedidoDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.dao.PersistivelContextual;
import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import javafx.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;

public class HandlerPersistencia extends PagamentoInBaseHandler {

    Persistivel<Pagamento, Long> pagamentoDAO;
    Persistivel<Pedido, Long> pedidoDAO;
    PersistivelContextual<Carrinho, Pair<Long, String>> carrinhoDAO;

    public HandlerPersistencia(JdbcTemplate jdbcTemplate, PagamentoProdutor pagamentoProdutor) {
        this.pagamentoDAO = new PagamentoDAO(jdbcTemplate);
        this.pedidoDAO = new PedidoDAO(jdbcTemplate);
        this.carrinhoDAO = new CarrinhoDAO(jdbcTemplate);
    }

    @Override
    public PagamentoDTO handle(Pagamento pagamento) {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        Pedido pedidoRetornado = this.pedidoDAO.salvar(pagamento.getPedido());
        Pagamento pagamentoRetornado = this.pagamentoDAO.salvar(pagamento);


        //Pagamento
        pagamentoDTO.setValidadeCartao(pagamento.getValidadeCartao());
        pagamentoDTO.setCvv(pagamento.getCvv());
        pagamentoDTO.setNumeroCartao(pagamento.getNumeroCartao());
        pagamentoDTO.setNomeCartao(pagamento.getNomeCartao());
        pagamentoDTO.setData(pagamento.getData());
        pagamentoDTO.setIdCobranca(pagamento.getPedido().getCobranca());
        pagamentoDTO.setCredId(pagamento.getCreditCardId());

        //Endereco
        pagamentoDTO.setUF(pagamento.getPedido().getCliente().getEndereco().getUF());
        pagamentoDTO.setBairro(pagamento.getPedido().getCliente().getEndereco().getBairro());
        pagamentoDTO.setComplemento(pagamento.getPedido().getCliente().getEndereco().getComplemento());
        pagamentoDTO.setCidade(pagamento.getPedido().getCliente().getEndereco().getCidade());
        pagamentoDTO.setCodigoPostal(pagamento.getPedido().getCliente().getEndereco().getCep());
        pagamentoDTO.setNumeroCasa(pagamento.getPedido().getCliente().getEndereco().getNumero());
        pagamentoDTO.setRua(pagamento.getPedido().getCliente().getEndereco().getLogradouro());

        //Pedido
        pagamentoDTO.setEmail(pagamento.getPedido().getCliente().getEmail());
        pagamentoDTO.setCpfCliente(pagamento.getPedido().getCliente().getCpf());
        pagamentoDTO.setStatus(pagamento.getStatusPagamento().name());
        pagamentoDTO.setNumeroPedido(pagamento.getPedido().getNumeroPedido());
        pagamentoDTO.setValorTotalPedido(pagamento.getPedido().getValorTotal());



        return null;
    }


}
