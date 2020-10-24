package com.example.rabbitmq_boot.rabbitmq.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;


@Component
@RabbitListener(queues = "queue_topic.woman")
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicwomanReceiver消费者收到消息  : " + testMessage.toString());
    }
}
