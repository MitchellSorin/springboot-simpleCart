package com.sorin.simplecart.activemq.original;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 消费者
 *
 * @author LSD
 * @date 2020/03/05
 **/
public class ActiveMQConsumer {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), (r) -> new Thread(r, "mq"));
        threadPool.execute(() -> {
            new QueueConsumer().listenMsg();
        });
        threadPool.execute(() -> {
            new TopicConsumer().listenMsg();
        });
    }

    public static class QueueConsumer {
        private void listenMsg() {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            Connection connection = null;
            Session session = null;
            Destination destination;
            MessageConsumer consumer = null;
            try {
                connection = factory.createConnection();
                connection.start();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                destination = session.createQueue("default-queue");
                consumer = session.createConsumer(destination);
                consumer.setMessageListener(message -> {
                    TextMessage result = (TextMessage) message;
                    try {
                        System.out.println(result.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class TopicConsumer {
        private void listenMsg() {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            Connection connection = null;
            Session session = null;
            Destination destination;
            MessageConsumer consumer = null;
            try {
                connection = factory.createConnection();
                connection.start();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                destination = session.createTopic("default-topic");
                consumer = session.createConsumer(destination);
                consumer.setMessageListener(message -> {
                    TextMessage result = (TextMessage) message;
                    try {
                        System.out.println(result.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
