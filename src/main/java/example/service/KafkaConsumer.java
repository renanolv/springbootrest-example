/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.service;

import java.util.Collections;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author renan
 */
public class KafkaConsumer {

    @Value("${kafka.broker.adress}")
    private String brokerAddress;

    @Value("${topic}")
    private String topic;

    private Properties props;
    private Consumer<String, String> consumer;

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @PostConstruct
    public void init() {

        props = new Properties();

        props.put("bootstrap.servers", brokerAddress);

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        props.put("group.id", "employee");
        props.put("retries", "1");
        props.put("linger.ms", 5);
        props.setProperty("enable.auto.commit", "false");
        
        props.setProperty("auto.offset.reset", "earliest");

    }

    public void consume() {

        log.info("Starting consumer... ");

        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        while (true) {

            ConsumerRecords<String, String> records = consumer.poll(1000);

            for (ConsumerRecord<String, String> record : records) {

                System.out.println("Partition: " + record.partition()
                        + ", Offset: " + record.offset()
                        + ", Key: " + record.key()
                        + ", Value: " + record.value());

            }

        }
    }
}
