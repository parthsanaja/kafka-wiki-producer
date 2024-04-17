package com.example.kafkawikiproducer;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaMessageSender {

    private KafkaProducer<String,String> kafkaProducer;

    @Autowired
    public KafkaMessageSender(@Autowired KafkaConfiguration kafkaConfiguration){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaConfiguration.bootstrapServer);
        configProps.put("sasl.mechanism", kafkaConfiguration.saslMechanism);
        configProps.put("security.protocol", kafkaConfiguration.securityProtocol);
        configProps.put("sasl.jaas.config", kafkaConfiguration.saslJaasConfig);

        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);

        configProps.put("client.id", "Client007");
        this.kafkaProducer = new KafkaProducer<>(configProps);
    }

    public void sendMessage(String topic, String key, String message){
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key,message);

        kafkaProducer.send(producerRecord, (recordMetadata, e) -> {
            if(e != null){
                System.out.println("there is an error");
                e.printStackTrace();
            }else{
                System.out.println(" Ack received. Offset is" + recordMetadata.offset() +
                        " Partiation is " + recordMetadata.partition() +
                        "and toString version is" + recordMetadata.toString());
            }

        });
    }

    public void close(){
        System.out.println("closing Kafka producer");
        kafkaProducer.close();;
    }
}
