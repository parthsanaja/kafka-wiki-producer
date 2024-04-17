package com.example.kafkawikiproducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfiguration {

    @Value("${bootstrap.servers}")
    public String bootstrapServer;

    @Value("${sasl.mechanism}")
    public String saslMechanism;

    @Value("${security.protocol}")
    public String securityProtocol;

    @Value("${sasl.jaas.config}")
    public String saslJaasConfig;
}
