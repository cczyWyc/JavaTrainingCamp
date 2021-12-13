package com.cczywyc.springbootactivemq.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangyc
 */
@Component
public class JmsProducer {

    /** jms template */
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String topic, final Map<String, String> message) {
        jmsTemplate.convertAndSend(topic, message);
    }
}
