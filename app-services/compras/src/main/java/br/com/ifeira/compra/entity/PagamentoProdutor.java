package br.com.ifeira.compra.entity;

import br.com.ifeira.compra.config.QueueConfig;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoProdutor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private QueueConfig config;

    public PagamentoDTO enfileiraPagamento(Pagamento pagamento){
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

        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_PENDENTES_KEY_NAME,
                pagamentoDTO);

        return pagamentoDTO;
    }

}
