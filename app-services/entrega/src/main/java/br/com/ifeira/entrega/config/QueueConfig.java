package br.com.ifeira.entrega.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueueConfig {

    public final String PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME;
    public final String PAGAMENTOS_CONCLUIDOS_QUEUE_NAME;
    public final String PAGAMENTOS_CONCLUIDOS_KEY_NAME;

    public QueueConfig(@Value("${mq[0].exchange-name}") String PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME,
                       @Value("${mq[0].queue-name}") String PAGAMENTOS_CONCLUIDOS_QUEUE_NAME,
                       @Value("${mq[0].routing-key}") String PAGAMENTOS_CONCLUIDOS_KEY_NAME) {
        this.PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME = PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME;
        this.PAGAMENTOS_CONCLUIDOS_QUEUE_NAME = PAGAMENTOS_CONCLUIDOS_QUEUE_NAME;
        this.PAGAMENTOS_CONCLUIDOS_KEY_NAME = PAGAMENTOS_CONCLUIDOS_KEY_NAME;
    }

    @Bean
    public Queue queueConcluidos() {
        return new Queue(PAGAMENTOS_CONCLUIDOS_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange exchangeConcluidos() {
        return new TopicExchange(PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingConcluidos(@Qualifier("queueConcluidos") Queue queue, @Qualifier("exchangeConcluidos") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(PAGAMENTOS_CONCLUIDOS_KEY_NAME);
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
