package com.cczywyc.springbootactivemq.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangyc
 */
@Component
public class JmsConsumer {

    /** jms template */
    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "active_mq_test")
    public void receiveMessage(final Map<String, String> message) {
        System.out.println(message.toString());
    }
}
