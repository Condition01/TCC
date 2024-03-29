package br.com.ifeira.compra.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class QueueConfig {

    public final String PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME;
    public final String PAGAMENTOS_PENDENTES_QUEUE_NAME;
    public final String PAGAMENTOS_PENDENTES_KEY_NAME;

    public QueueConfig(@Value("${mq[0].exchange-name}") String PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME,
                       @Value("${mq[0].queue-name}") String PAGAMENTOS_PENDENTES_QUEUE_NAME,
                       @Value("${mq[0].routing-key}") String PAGAMENTOS_PENDENTES_KEY_NAME) {
        this.PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME = PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME;
        this.PAGAMENTOS_PENDENTES_QUEUE_NAME = PAGAMENTOS_PENDENTES_QUEUE_NAME;
        this.PAGAMENTOS_PENDENTES_KEY_NAME = PAGAMENTOS_PENDENTES_KEY_NAME;
    }

    @Bean
    public Queue queuePendentes() {
        return new Queue(PAGAMENTOS_PENDENTES_QUEUE_NAME, true);
    }

    @Bean
    public CustomExchange exchangePendentes() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingPendentes(@Qualifier("queuePendentes") Queue queue, @Qualifier("exchangePendentes") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(PAGAMENTOS_PENDENTES_KEY_NAME).noargs();
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
