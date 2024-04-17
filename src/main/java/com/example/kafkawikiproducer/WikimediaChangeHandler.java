package com.example.kafkawikiproducer;


import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WikimediaChangeHandler  implements EventHandler {

    @Autowired
    private KafkaMessageSender kafkaMessageSender;

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {
        System.out.println("WikimediaChangeHandler invoking close on kafka producer");
        kafkaMessageSender.close();;
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        kafkaMessageSender.sendMessage("first_topic", null,messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
