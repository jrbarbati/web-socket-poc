package com.personal.batch.hatcher.receive.service;

import com.personal.batch.hatcher.publish.message.BatchRunResponse;
import com.personal.batch.hatcher.receive.message.BatchRunRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class BatchRunService extends BidirectionalWebSocketService<BatchRunRequest, BatchRunResponse>
{
    private final HeartbeatService heartbeatService;

    @Autowired
    public BatchRunService(SimpMessagingTemplate simpMessagingTemplate, HeartbeatService heartbeatService)
    {
        super(simpMessagingTemplate);
        this.heartbeatService = heartbeatService;
    }

    public BatchRunResponse process(BatchRunRequest batchRunRequest)
    {
        return BatchRunResponse.builder()
                .correlationId(batchRunRequest.getCorrelationId())
                .instanceId(batchRunRequest.getInstanceId())
                .name(batchRunRequest.getName())
                .orgId(batchRunRequest.getOrgId())
                .shouldRun(heartbeatService.isOnline(batchRunRequest.getInstanceId()))
                .build();
    }
}
