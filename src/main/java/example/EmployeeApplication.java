package example;

import example.service.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EmployeeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EmployeeApplication.class, args);
        
        
        boolean shouldConsume = context.getEnvironment().getProperty("kafka.consumer.enabled", Boolean.class, Boolean.TRUE);
        
        if(shouldConsume && context.containsBean("kafkaConsumer")){
            
            KafkaConsumer consumer = context.getBean("kafkaConsumer", KafkaConsumer.class);
            
            consumer.consume();
                    
            
        }

    }
}
