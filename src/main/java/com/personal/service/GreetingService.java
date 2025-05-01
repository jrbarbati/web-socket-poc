package com.personal.service;

import com.personal.message.receive.Hello;
import com.personal.message.publish.Greeting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GreetingService extends WebSocketService<Hello, Greeting>
{
    private static final Logger log = LogManager.getLogger(GreetingService.class);

    @Autowired
    public GreetingService(SimpMessagingTemplate messagingTemplate)
    {
        super(messagingTemplate);
    }

    @Override
    public Greeting process(Hello message)
    {
        return Greeting.builder()
                .content("Hello, %s!".formatted(message.getName()))
                .build();
    }
}
