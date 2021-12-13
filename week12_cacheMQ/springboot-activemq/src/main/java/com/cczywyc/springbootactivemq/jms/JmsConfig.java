package com.cczywyc.springbootactivemq.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * jms configuration
 *
 * @author wangyc
 */
@Configuration
public class JmsConfig {

    /** active mq connection url */
    private final String BROKER_URL = "tcp://121.40.215.120:61616";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory listenerContainerFactory = new DefaultJmsListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory());
        listenerContainerFactory.setConcurrency("1-1");
        return listenerContainerFactory;
    }
}
