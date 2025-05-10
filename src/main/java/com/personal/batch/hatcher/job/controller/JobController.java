package com.personal.batch.hatcher.job.controller;

import com.personal.batch.hatcher.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/clients/{clientId}")
public class JobController
{
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService)
    {
        this.jobService = jobService;
    }

    @GetMapping(path = "/jobs", produces = "application/json")
    public ResponseEntity<Object> fetchJobs(@PathVariable Long clientId)
    {
        try
        {
            return ResponseEntity.ok(jobService.findByClientId(clientId));
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("{\"message\": \"%s\"}".formatted(e.getMessage()));
        }
    }
}
