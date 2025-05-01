package com.personal.batch.hatcher.receive.service;

import com.personal.batch.hatcher.publish.message.PublishableMessage;
import com.personal.batch.hatcher.receive.message.ReceivableMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public abstract class BidirectionalWebSocketService<T extends ReceivableMessage, U extends PublishableMessage>
{
    private final SimpMessagingTemplate messagingTemplate;

    public BidirectionalWebSocketService(SimpMessagingTemplate messagingTemplate)
    {
        this.messagingTemplate = messagingTemplate;
    }

    public SimpMessagingTemplate getMessagingTemplate()
    {
        return messagingTemplate;
    }

    public abstract U process(T message);
}
