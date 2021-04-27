package br.com.ifeira.pagamento.entity;

import br.com.ifeira.pagamento.config.QueueConfig;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentosRealizadosProdutor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private QueueConfig config;

    public void enfileirarPagamentosConcluidos(PagamentoDTO pagamento) {
        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_CONCLUIDOS_KEY_NAME,
                pagamento);
    }

    public void enfileirarPagamentosComErro(PagamentoDTO pagamento) {
        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_PENDENTES_KEY_NAME,
                pagamento, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setHeader("x-delay", "900000");
                        return message;
                    }
                });
    }
}
