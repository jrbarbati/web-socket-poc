package com.personal.batch.hatcher.receive.service;

import com.personal.batch.hatcher.receive.message.ReceivableMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public abstract class UnidirectionalWebSocketService<T extends ReceivableMessage>
{
    private final SimpMessagingTemplate messagingTemplate;

    public UnidirectionalWebSocketService(SimpMessagingTemplate messagingTemplate)
    {
        this.messagingTemplate = messagingTemplate;
    }

    public SimpMessagingTemplate getMessagingTemplate()
    {
        return messagingTemplate;
    }

    public abstract void process(T message);
}
