package com.personal.batch.hatcher.receive.service;

import com.personal.batch.hatcher.client.service.ClientService;
import com.personal.batch.hatcher.receive.message.Heartbeat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class HeartbeatService extends UnidirectionalWebSocketService<Heartbeat>
{
    private static final Logger log = LogManager.getLogger(HeartbeatService.class);
    private static final Long TEN_SECONDS_MILLIS = 10000L;

    private final ClientService clientService;

    @Autowired
    public HeartbeatService(SimpMessagingTemplate messagingTemplate, ClientService clientService)
    {
        super(messagingTemplate);
        this.clientService = clientService;
    }

    @Override
    public void process(Heartbeat message)
    {
        log.info("Heartbeat received from {}.", message.getInstanceId());
        clientService.updateLastHeartbeatTimestamp(message.getInstanceId(), currentTime());
    }

    public boolean isOnline(UUID instanceId)
    {
        return isOnline(instanceId, TEN_SECONDS_MILLIS);
    }

    public boolean isOnline(UUID instanceId, long heartbeatTimeoutMillis)
    {
        return clientService.findByInstanceId(instanceId)
                .filter(value -> currentTime().getTime() - value.getLastHeartbeatTimestamp().getTime() <= heartbeatTimeoutMillis)
                .isPresent();
    }

    protected Timestamp currentTime()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}
