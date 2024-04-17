package com.example.kafkawikiproducer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class WikiStreamInvocationHandler {

    private EventSource eventSource;

    private final String wikiStreamUrl = "https://stream.wikimedia.org/v2/stream/recentchange";


    @Autowired
    public WikiStreamInvocationHandler(@Autowired EventHandler eventHandler){
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(wikiStreamUrl));
        this.eventSource = builder.build();
        eventSource.start();
    }

    @PreDestroy
    public void close(){
        System.out.println("destroy of event source");
        eventSource.close();
    }


}
