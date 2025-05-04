package com.personal.batch.hatcher.job.service;

import com.personal.batch.hatcher.job.model.Job;
import com.personal.batch.hatcher.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService
{
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository)
    {
        this.jobRepository = jobRepository;
    }

    public List<Job> findByClientId(UUID clientId)
    {
        return jobRepository.findByClientId(clientId);
    }
}
