package br.com.ifeira.pagamento.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class QueueConfig {

    public final String TOPIC_EXCHANGE_NAME;
    public final String QUEUE_NAME;
    public final String KEY_NAME;

    public QueueConfig(@Value("${mq[0].exchange-name}") String TOPIC_EXCHANGE_NAME,
                       @Value("${mq[0].queue-name}") String QUEUE_NAME,
                       @Value("${mq[0].routing-key}") String KEY_NAME) {
        this.TOPIC_EXCHANGE_NAME = TOPIC_EXCHANGE_NAME;
        this.QUEUE_NAME = QUEUE_NAME;
        this.KEY_NAME = KEY_NAME;
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(KEY_NAME);
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
