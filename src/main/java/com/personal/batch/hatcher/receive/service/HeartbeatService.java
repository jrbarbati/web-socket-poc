package com.personal.batch.hatcher.receive.service;

import com.personal.batch.hatcher.receive.message.Heartbeat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

@Service
public class HeartbeatService extends UnidirectionalWebSocketService<Heartbeat>
{
    private static final Logger log = LogManager.getLogger(HeartbeatService.class);
    private static final Long TEN_SECONDS_MILLIS = 10000L;

    private final HashMap<UUID, Timestamp> lastHeartbeat = new HashMap<>();

    @Autowired
    public HeartbeatService(SimpMessagingTemplate messagingTemplate)
    {
        super(messagingTemplate);
    }

    @Override
    public void process(Heartbeat message)
    {
        log.info("Heartbeat received from {}.", message.getInstanceId());
        lastHeartbeat.put(message.getInstanceId(), currentTime());
    }

    public boolean isOnline(UUID instanceId)
    {
        return isOnline(instanceId, TEN_SECONDS_MILLIS);
    }

    public boolean isOnline(UUID instanceId, long heartbeatTimeoutMillis)
    {
        return lastHeartbeat.containsKey(instanceId)
                && currentTime().getTime() - lastHeartbeat.get(instanceId).getTime() <= heartbeatTimeoutMillis;
    }

    protected Timestamp currentTime()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}
