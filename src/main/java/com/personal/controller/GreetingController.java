package com.personal.controller;

import com.personal.message.receive.Hello;
import com.personal.message.publish.Greeting;
import com.personal.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController
{
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService)
    {
        this.greetingService = greetingService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Hello message)
    {
        return greetingService.process(message);
    }
}
