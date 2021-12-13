package com.cczywyc.springbootactivemq;

import com.cczywyc.springbootactivemq.jms.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJms
public class SpringbootActivemqApplication implements ApplicationRunner {

    /** jms producer */
    @Autowired
    private JmsProducer jmsProducer;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActivemqApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String topic = "active_mq_test";
        Map<String, String> message = new HashMap<>();
        message.put("cczyWyc", "cczyWyc");
        jmsProducer.sendMessage(topic, message);
    }
}
