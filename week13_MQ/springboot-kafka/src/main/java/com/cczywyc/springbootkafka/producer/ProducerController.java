package com.cczywyc.springbootkafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * producer
 *
 * @author wangyc
 */
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/send/{message}")
    public String sendMsg(@PathVariable String msg) {
        kafkaTemplate.send("cczyWyc", msg);
        String response = "Message:" + msg + "send success!";
        return response;
    }
}
