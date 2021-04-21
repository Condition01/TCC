package br.com.ifeira.pagamento.boundary;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConsumer {

    private Logger logger = LoggerFactory.getLogger(PagamentoConsumer.class);

    @RabbitListener(queues = "${mq[0].queue-name}")
    public void consumir(PagamentoDTO pagamento) {


    }

}
