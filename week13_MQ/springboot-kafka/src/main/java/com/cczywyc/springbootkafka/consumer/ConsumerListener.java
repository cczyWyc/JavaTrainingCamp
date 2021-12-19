package com.cczywyc.springbootkafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * consumer
 *
 * @author wangyc
 */
@Component
public class ConsumerListener {
    @KafkaListener(topics = "cczyWyc", groupId = "consumer-group")
    public void listen(List<String> list, Acknowledgment acknowledgment) {
        List<String> msgList = new ArrayList<>();
        for (String record : msgList) {
            Optional<?> kafkaMessage = Optional.ofNullable(record);
            kafkaMessage.ifPresent(o -> msgList.add(o.toString()));
        }
        if (msgList.size() > 0) {
            for (String message : msgList) {
                System.out.println("start consume message:" + message);
            }
        }
        acknowledgment.acknowledge();
        msgList.clear();
        System.out.println("finish");
    }
}
