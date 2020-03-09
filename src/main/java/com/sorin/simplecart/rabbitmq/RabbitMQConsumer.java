package com.sorin.simplecart.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author LSD
 * @date 2020/03/07
 **/
@Component
public class RabbitMQConsumer {

    //---------------------direct-----------------------------
    @RabbitListener(queues = "my-direct-queue")
    @RabbitHandler
    public void directReceiver1(String msg) {
        System.out.println("d1收到消息：" + msg);
    }

    @RabbitListener(queues = "my-direct-queue")
    @RabbitHandler
    public void directReceiver2(String msg) {
        System.out.println("d2收到消息：" + msg);
    }


    //---------------------topic-----------------------------
    @RabbitListener(queues = "my-topic-queue")
    @RabbitHandler
    public void topicReceiver1(String msg) {
        System.out.println("t1收到消息：" + msg);
    }

    @RabbitListener(queues = "my-topic-queue")
    @RabbitHandler
    public void topicReceiver2(String msg) {
        System.out.println("t2收到消息：" + msg);
    }

    @RabbitListener(queues = "my-topic-queue3")
    @RabbitHandler
    public void topicReceiver3(String msg) {
        System.out.println("t3收到消息：" + msg);
    }

    //---------------------fanout-----------------------------
    @RabbitListener(queues = "my-fanout-queue")
    @RabbitHandler
    public void fanoutReceiver1(String msg) {
        System.out.println("f1收到消息：" + msg);
    }

    @RabbitListener(queues = "my-fanout-queue2")
    @RabbitHandler
    public void fanoutReceiver2(String msg) {
        System.out.println("f2收到消息：" + msg);
    }
}
