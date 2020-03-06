package com.sorin.simplecart.activemq.original;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产者
 *
 * @author LSD
 * @date 2020/03/05
 **/
public class Producer {

    public static void main(String[] args) {
        QueueProducer producer = new QueueProducer();
        producer.sendMsg("hello, this is a queue message");
        TopicProducer topicProducer = new TopicProducer();
        topicProducer.sendMsg("hello, this is a topic message");
    }

    public static class QueueProducer {
        private void sendMsg(String msg) {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            Connection connection = null;
            Session session = null;
            Destination destination;
            MessageProducer producer = null;
            try {
                connection = factory.createQueueConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                destination = session.createQueue("default-queue");
                producer = session.createProducer(destination);
                TextMessage message = session.createTextMessage(msg);
                producer.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != producer) {
                    try {
                        producer.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
                if (null != session) {
                    try {
                        session.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
                if (null != connection) {
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class TopicProducer {
        private void sendMsg(String msg) {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            Connection connection = null;
            Session session = null;
            Destination destination;
            MessageProducer producer = null;
            try {
                connection = factory.createQueueConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                destination = session.createTopic("default-topic");
                producer = session.createProducer(destination);
                TextMessage message = session.createTextMessage(msg);
                producer.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != producer) {
                    try {
                        producer.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
                if (null != session) {
                    try {
                        session.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
                if (null != connection) {
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
