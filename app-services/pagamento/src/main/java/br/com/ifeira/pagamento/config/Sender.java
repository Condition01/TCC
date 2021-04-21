//package br.com.ifeira.pagamento.config;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Sender implements CommandLineRunner {
//
//
//    @Autowired
//    private QueueConfig queueConfig;
//
//    private final RabbitTemplate rabbitTemplate;
//    private final Receiver receiver;
//
//    public Sender(Receiver receiver, RabbitTemplate rabbitTemplate) {
//        this.receiver = receiver;
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
////        System.out.println("Sending message...");
////        rabbitTemplate.convertAndSend(queueConfig.TOPIC_EXCHANGE_NAME), queueConfig.KEY_NAME, "Hello from RabbitMQ!");
////        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//    }
//
//}
