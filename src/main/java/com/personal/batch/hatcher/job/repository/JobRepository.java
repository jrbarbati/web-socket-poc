package com.personal.batch.hatcher.job.repository;

import com.personal.batch.hatcher.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface JobRepository extends JpaRepository<Job, Long>
{
    List<Job> findByClientId(Long clientId);

    List<Job> findByNameAndRequestedAtAfter(String name, Timestamp requestedAfter);
}
