package br.com.ifeira.compra.entity;

import br.com.ifeira.compra.boundary.CarrinhoBoundary;
import br.com.ifeira.compra.config.QueueConfig;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoProdutor {

    private Logger logger = LoggerFactory.getLogger(CarrinhoBoundary.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private QueueConfig config;

    public PagamentoDTO enfileiraPagamento(Pagamento pagamento) {
        PagamentoDTO pagamentoDTO = criarDTO(pagamento);

        logger.info("Inserindo pagamento na fila:");
        logger.info(pagamentoDTO.toString());

        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_PENDENTES_KEY_NAME,
                pagamentoDTO);

        return pagamentoDTO;
    }

    public PagamentoDTO criarDTO(Pagamento pagamento) {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();

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
        pagamentoDTO.setIdPagamento(pagamento.getId());
        pagamentoDTO.setEmail(pagamento.getPedido().getCliente().getEmail());
        pagamentoDTO.setCpfCliente(pagamento.getPedido().getCliente().getCpf());
        pagamentoDTO.setStatus(pagamento.getStatusPagamento().name());
        pagamentoDTO.setNumeroPedido(pagamento.getPedido().getNumeroPedido());
        pagamentoDTO.setValorTotalPedido(pagamento.getPedido().getValorTotal());

        return pagamentoDTO;
    }

}
