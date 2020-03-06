package com.sorin.simplecart.activemq.current;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author LSD
 * @date 2020/03/06
 **/
@Component
public class Consumer {

    @JmsListener(destination = "my-queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("out-queue")
    public String queueReceiver1(String text) {
        System.out.println("q1收到队列消息：" + text);
        return "q1";
    }

    @JmsListener(destination = "my-queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("out-queue")
    public String queueReceiver2(String text) {
        System.out.println("q2收到队列消息：" + text);
        return "q2";
    }

    @JmsListener(destination = "my-topic", containerFactory = "jmsListenerContainerTopic")
    @SendTo("out-topic")
    public String topicReceiver1(String text) {
        System.out.println("t1收到主题消息：" + text);
        return "t1";
    }

    @JmsListener(destination = "my-topic", containerFactory = "jmsListenerContainerTopic")
    @SendTo("out-topic")
    public String topicReceiver2(String text) {
        System.out.println("t2收到主题消息：" + text);
        return "t2";
    }
}
