package example.service;

import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


/**
 *
 * @author renan
 */
@Service
@ConditionalOnProperty(name = "kafka.enabled", matchIfMissing = true)
public class KafkaProducer {

    @Value("${kafka.broker.address}")
    private String brokerAddress;

    @Value("${topic}")
    private String topic;

    private Producer<String, String> producer;

    @PostConstruct
    public void init() {

        Properties props = new Properties();

        props.put("bootstrap.servers", brokerAddress);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "1");
        props.put("retries", "1");
        props.put("linger.ms", 5);
        
      
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        
        
        for(int key=0; key < 5; key++){
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, Integer.toString(key), "message that has key: "+ Integer.toBinaryString(key));
            producer.send(producerRecord);

        }
        
        producer.close();
        
    }

}
