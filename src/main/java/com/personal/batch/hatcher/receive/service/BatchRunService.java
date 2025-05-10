package com.personal.batch.hatcher.receive.service;

import com.personal.batch.hatcher.client.model.Client;
import com.personal.batch.hatcher.client.service.ClientService;
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
    private final ClientService clientService;
    private final JobService jobService;

    @Autowired
    public BatchRunService(
            SimpMessagingTemplate simpMessagingTemplate,
            HeartbeatService heartbeatService,
            ClientService clientService,
            JobService jobService
    )
    {
        super(simpMessagingTemplate);
        this.heartbeatService = heartbeatService;
        this.clientService = clientService;
        this.jobService = jobService;
    }

    public BatchRunResponse process(BatchRunRequest batchRunRequest) throws Exception
    {
        BatchRunResponse.Builder batchRunResponseBuilder = BatchRunResponse.builder()
                .correlationId(batchRunRequest.getCorrelationId())
                .instanceId(batchRunRequest.getInstanceId())
                .name(batchRunRequest.getName())
                .orgId(batchRunRequest.getOrgId());

        Client client = clientService.findByInstanceId(batchRunRequest.getInstanceId()).orElse(null);

        if (client == null)
            return batchRunResponseBuilder
                    .shouldRun(false)
                    .build();

        // TODO: Getting a Caught InvalidDataAccessApiUsageException trying to process BatchRunRequest. detached entity passed to persist: com.personal.batch.hatcher.client.model.Client
        //  here. Need to figure out a solution.
        Job requested = jobService.save(Job.builder()
                .client(client)
                .name(batchRunRequest.getName())
                .orgId(batchRunRequest.getOrgId())
                .status(JobStatus.REQUESTED)
                .requestedAt(currentTimeMinus(0L))
                .build());

        boolean shouldRun = shouldRun(batchRunRequest);

        if (shouldRun)
            requested.setStatus(JobStatus.STARTED);
        else
            requested.setStatus(JobStatus.DENIED);

        jobService.save(requested);

        return batchRunResponseBuilder
                .shouldRun(shouldRun)
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

        if (jobWithOrdIdAlreadyRequestedOrStarted(batchRunRequest, jobs))
        {
            log.trace("Requested job {} has already been requested or is already running for orgId {}.", batchRunRequest.getName(), batchRunRequest.getOrgId());
            return false;
        }

        log.trace("Allowing job {} to run for orgId {} to run on instance {}.", batchRunRequest.getName(), batchRunRequest.getOrgId(), batchRunRequest.getInstanceId());

        return true;
    }

    protected boolean jobWithOrdIdAlreadyRequestedOrStarted(BatchRunRequest batchRunRequest, List<Job> jobs)
    {
        return jobs.stream().anyMatch(job -> Objects.equals(job.getOrgId(), batchRunRequest.getOrgId())
                &&
                (Objects.equals(job.getStatus(), JobStatus.REQUESTED))
                || Objects.equals(job.getStatus(), JobStatus.STARTED));
    }

    private Timestamp currentTimeMinus(long millis)
    {
        return new Timestamp(System.currentTimeMillis() - millis);
    }
}
