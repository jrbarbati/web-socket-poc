package com.personal.service;

import com.personal.message.publish.PublishableMessage;
import com.personal.message.receive.ReceivableMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public abstract class WebSocketService<T extends ReceivableMessage, U extends PublishableMessage>
{
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate)
    {
        this.messagingTemplate = messagingTemplate;
    }

    public SimpMessagingTemplate getMessagingTemplate()
    {
        return messagingTemplate;
    }

    public abstract U process(T message);
}
