package com.example.kafkawikiproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class KafkaWikiProducerApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(KafkaWikiProducerApplication.class, args);

        Thread.sleep(Duration.ofSeconds(10).toMillis());
    }

}
