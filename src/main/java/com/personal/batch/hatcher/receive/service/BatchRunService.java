package com.personal.batch.hatcher.receive.service;

import com.personal.batch.hatcher.job.model.Job;
import com.personal.batch.hatcher.job.model.JobStatus;
import com.personal.batch.hatcher.job.service.JobService;
import com.personal.batch.hatcher.publish.message.BatchRunResponse;
import com.personal.batch.hatcher.receive.message.BatchRunRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class BatchRunService extends BidirectionalWebSocketService<BatchRunRequest, BatchRunResponse>
{
    private static final Logger log = LogManager.getLogger(BatchRunService.class);

    private static final Long THREE_MINS_MILLIS = 180000L;

    private final HeartbeatService heartbeatService;
    private final JobService jobService;

    @Autowired
    public BatchRunService(SimpMessagingTemplate simpMessagingTemplate, HeartbeatService heartbeatService, JobService jobService)
    {
        super(simpMessagingTemplate);
        this.heartbeatService = heartbeatService;
        this.jobService = jobService;
    }

    public BatchRunResponse process(BatchRunRequest batchRunRequest)
    {
        return BatchRunResponse.builder()
                .correlationId(batchRunRequest.getCorrelationId())
                .instanceId(batchRunRequest.getInstanceId())
                .name(batchRunRequest.getName())
                .orgId(batchRunRequest.getOrgId())
                .shouldRun(shouldRun(batchRunRequest))
                .build();
    }

    protected boolean shouldRun(BatchRunRequest batchRunRequest)
    {
        log.trace("Determining if batch should run on instance {} for name {} and orgId {}", batchRunRequest.getInstanceId(), batchRunRequest.getName(), batchRunRequest.getOrgId());

        if (!heartbeatService.isOnline(batchRunRequest.getInstanceId()))
        {
            log.trace("Instance {} is not online. Denying request.", batchRunRequest.getInstanceId());
            return false;
        }

        List<Job> jobs = jobService.findByNameAndRequestedAfter(batchRunRequest.getName(), currentTimeMinus(THREE_MINS_MILLIS));

        if (jobWithOrdIdAlreadyRunning(batchRunRequest, jobs))
        {
            log.trace("Requested job {} is already running for orgId {}.", batchRunRequest.getName(), batchRunRequest.getOrgId());
            return false;
        }

        log.trace("Allowing job {} to run for orgId {} to run on instance {}.", batchRunRequest.getName(), batchRunRequest.getOrgId(), batchRunRequest.getInstanceId());

        return true;
    }

    protected boolean jobWithOrdIdAlreadyRunning(BatchRunRequest batchRunRequest, List<Job> jobs)
    {
        return jobs.stream().anyMatch(job -> Objects.equals(job.getOrgId(), batchRunRequest.getOrgId())
                && Objects.equals(job.getStatus(), JobStatus.STARTED));
    }

    private Timestamp currentTimeMinus(long millis)
    {
        return new Timestamp(System.currentTimeMillis() - millis);
    }
}
