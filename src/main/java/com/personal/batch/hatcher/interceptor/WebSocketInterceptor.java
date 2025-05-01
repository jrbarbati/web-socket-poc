package com.personal.batch.hatcher.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

@Service
public class WebSocketInterceptor implements ChannelInterceptor
{
    private static final Logger log = LogManager.getLogger(WebSocketInterceptor.class);

    @Override
    public boolean preReceive(MessageChannel channel)
    {
        return true;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel)
    {
        if (message.getHeaders().containsKey("stompCommand") && message.getHeaders().get("stompCommand").equals("DISCONNECT"))
            log.info("DISCONNECTED.");

        return message;
    }
}
