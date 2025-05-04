package com.personal.batch.hatcher.job.repository;

import com.personal.batch.hatcher.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface JobRepository extends JpaRepository<Job, Long>
{
    List<Job> findByClientId(UUID clientId);
}
