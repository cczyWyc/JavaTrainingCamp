package com.cczyWyc.task.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * jms active MQ customer producer
 *
 * @author wangyc
 */
public class JmsMain {

    public static void main(String[] args) {
//        Destination destination = new ActiveMQTopic("cczyWyc.topic");
        Destination destination = new ActiveMQQueue("cczyWyc.queue");
        testActiveMQ(destination);
    }

    public static void testActiveMQ(Destination destination) {
        Connection connection = null;
        Session session = null;
        try {
            //create connection and session
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://xxx.xxx.xxx.xxx:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //create customer
            MessageConsumer consumer = session.createConsumer(destination);
            final AtomicInteger count = new AtomicInteger(0);
            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println(count.incrementAndGet() + " => receive from " + message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            consumer.setMessageListener(listener);

            //create producer
            MessageProducer producer = session.createProducer(destination);
            int index = 0;
            while (index++ < 50) {
                TextMessage message = session.createTextMessage(index + " message");
                producer.send(message);
            }
            Thread.sleep(20000);
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
