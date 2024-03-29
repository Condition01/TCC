package br.com.ifeira.entrega.boundary;

import br.com.ifeira.entrega.controller.EntregaController;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoRealizadoConsumidor {

    private Logger logger = LoggerFactory.getLogger(PagamentoRealizadoConsumidor.class);

    @Autowired
    private EntregaController entregaController;

    @RabbitListener(queues = "${mq[0].queue-name}")
    public void consumir(PagamentoDTO pagamento) throws Exception {
        logger.info("Consumindo pagamento concluído: " + pagamento.toString());
        logger.info("Pagamento ID: " + pagamento.getIdPagamento());
        this.entregaController.gerarEntrega(pagamento);
    }

}
