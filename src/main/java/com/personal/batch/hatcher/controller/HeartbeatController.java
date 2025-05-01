package com.personal.batch.hatcher.controller;

import com.personal.batch.hatcher.receive.message.Heartbeat;
import com.personal.batch.hatcher.receive.service.HeartbeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HeartbeatController
{
    private final HeartbeatService heartbeatService;

    @Autowired
    public HeartbeatController(HeartbeatService heartbeatService)
    {
        this.heartbeatService = heartbeatService;
    }

    @MessageMapping("/heartbeat")
    public void heartbeat(Heartbeat message)
    {
        heartbeatService.process(message);
    }
}
