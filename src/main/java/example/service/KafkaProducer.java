package example.service;


import example.model.User;
import flexjson.JSONSerializer;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
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

    private Producer<String, String> producer;

    private String topic;
   

    JSONSerializer json = new JSONSerializer();

    public KafkaProducer() {
        json.exclude("*.class");
    }

    @PostConstruct
    public void init() {

        Properties props = new Properties();

        props.put("bootstrap.servers", brokerAddress);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        props.put("acks", "1");
        props.put("retries", "1");
        props.put("linger.ms", 1);

        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }

    public void receiveUser(User user) {

        topic = "user";

        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, user.getId().toString(), json.serialize(user));
        producer.send(producerRecord);

    }

}
