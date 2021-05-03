package br.com.ifeira.compra.entity;

import br.com.ifeira.pagamento.config.QueueConfig;
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

    public void enfileiraPagamento(PagamentoDTO pagamento){
        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_CONCLUIDOS_KEY_NAME,
                pagamento);
    }

}
