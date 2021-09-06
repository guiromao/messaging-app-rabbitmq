package com.example.messagingrabbitmq;

import com.example.messagingrabbitmq.messaging.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate template;
    private final Receiver receiver;

    public Runner(RabbitTemplate template, Receiver receiver) {
        this.template = template;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        template.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "GAR Object",
                "A Hello World from the Messaging service!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
