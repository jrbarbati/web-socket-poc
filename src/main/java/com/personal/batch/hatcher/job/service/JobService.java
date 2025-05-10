package com.personal.batch.hatcher.job.service;

import com.personal.batch.hatcher.client.model.Client;
import com.personal.batch.hatcher.client.repository.ClientRepository;
import com.personal.batch.hatcher.client.service.ClientService;
import com.personal.batch.hatcher.job.exception.JobServiceException;
import com.personal.batch.hatcher.job.model.Job;
import com.personal.batch.hatcher.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class JobService
{
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository)
    {
        this.jobRepository = jobRepository;
    }

    public List<Job> findByClientId(Long clientId)
    {
        return jobRepository.findByClientId(clientId);
    }

    public List<Job> findByNameAndRequestedAfter(String name, Timestamp requestedAfter)
    {
        return jobRepository.findByNameAndRequestedAtAfter(name, requestedAfter);
    }

    public Job save(Job job)
    {
        return jobRepository.save(job);
    }
}
