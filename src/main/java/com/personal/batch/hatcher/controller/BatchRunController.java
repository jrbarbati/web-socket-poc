package com.personal.batch.hatcher.controller;

import com.personal.batch.hatcher.publish.message.BatchRunResponse;
import com.personal.batch.hatcher.receive.message.BatchRunRequest;
import com.personal.batch.hatcher.receive.service.BatchRunService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BatchRunController
{
    private static final Logger log = LogManager.getLogger(BatchRunController.class);
    private final BatchRunService batchRunService;

    @Autowired
    public BatchRunController(BatchRunService batchRunService)
    {
        this.batchRunService = batchRunService;
    }

    @MessageMapping("/batch-run")
    @SendTo("/topic/batch-run")
    public BatchRunResponse batchRun(BatchRunRequest request)
    {
        try
        {
            return batchRunService.process(request);
        }
        catch (Exception e)
        {
            log.error("Caught {} trying to process BatchRunRequest. {}", e.getClass().getSimpleName(), e.getMessage(), e);
            return BatchRunResponse.builder()
                    .correlationId(request.getCorrelationId())
                    .instanceId(request.getInstanceId())
                    .name(request.getName())
                    .orgId(request.getOrgId())
                    .shouldRun(false)
                    .build();
        }
    }
}
