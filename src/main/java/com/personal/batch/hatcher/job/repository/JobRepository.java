package com.personal.batch.hatcher.job.repository;

import com.personal.batch.hatcher.job.model.Job;
import com.personal.batch.hatcher.job.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public interface JobRepository extends JpaRepository<Job, Long>
{
    @Query("SELECT j from Job j JOIN FETCH j.client WHERE j.clientId = :clientId")
    List<Job> findByClientId(Long clientId);

    @Query("SELECT j FROM Job j JOIN FETCH j.client WHERE j.name = :name AND j.orgId = :orgId AND j.status in :statuses")
    List<Job> findByNameAndOrgIdAndStatusIn(String name, Integer orgId, Collection<JobStatus> statuses);
}
