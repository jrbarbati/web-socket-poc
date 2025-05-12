package com.personal.batch.hatcher.job.service;

import com.personal.batch.hatcher.client.service.ClientService;
import com.personal.batch.hatcher.job.exception.JobServiceException;
import com.personal.batch.hatcher.job.model.Job;
import com.personal.batch.hatcher.job.model.JobStatus;
import com.personal.batch.hatcher.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService
{
    private final ClientService clientService;
    private final JobRepository jobRepository;

    @Autowired
    public JobService(ClientService clientService, JobRepository jobRepository)
    {
        this.clientService = clientService;
        this.jobRepository = jobRepository;
    }

    public List<Job> findByClientId(Long clientId)
    {
        return jobRepository.findByClientId(clientId);
    }

    public List<Job> findByNameAndOrgIdAndStatusIn(String name, Integer orgId, List<JobStatus> statuses)
    {
        return jobRepository.findByNameAndOrgIdAndStatusIn(name, orgId, statuses);
    }

    @Transactional
    public Job save(Job job) throws JobServiceException
    {
        clientService.findById(job.getClientId()).ifPresent(job::setClient);

        if (job.getClient() == null)
            throw new JobServiceException("Unable to save job to a client that doesn't exist.");

        return jobRepository.save(job);
    }
}
