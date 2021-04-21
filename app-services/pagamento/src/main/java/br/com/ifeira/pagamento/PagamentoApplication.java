package br.com.ifeira.pagamento;

import br.com.ifeira.pagamento.config.QueueConfig;
import br.com.ifeira.pagamento.factories.PagamentoOutConcretHandlerFactory;
import br.com.ifeira.pagamento.factories.PagamentoOutHandlerFactory;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/teste")
public class PagamentoApplication {


	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private QueueConfig config;

	public static void main(String[] args) {
		SpringApplication.run(PagamentoApplication.class, args);
	}


	@GetMapping("/teste")
	void teste() {
		PagamentoDTO a = new PagamentoDTO();
		a.setCpfCliente("123123");

		PagamentoOutHandlerFactory pagFactory = new PagamentoOutConcretHandlerFactory();

		PagamentoOutHandler pagHandlers = pagFactory.criarPagamentoOutChain();
		pagHandlers.handle(new PagamentoDTO());

//		this.rabbitTemplate.convertAndSend(this.config.TOPIC_EXCHANGE_NAME, this.config.KEY_NAME, a);
		System.out.println("Sucesso");
	}

}



