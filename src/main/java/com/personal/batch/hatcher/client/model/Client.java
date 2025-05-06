package com.personal.batch.hatcher.client.model;

import com.personal.batch.hatcher.job.model.Job;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "CLIENT")
@Table(name = "CLIENT")
public class Client
{
    @Id
    private UUID id;

    @Column(updatable = false)
    private Timestamp createdAt;

    private Timestamp lastHeartbeatTimestamp;

    @OneToMany(mappedBy = "client")
    private List<Job> jobs;

    @PrePersist
    public void onCreate()
    {
        this.createdAt = new Timestamp(System.currentTimeMillis());;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public Timestamp getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt)
    {
        this.createdAt = createdAt;
    }

    public Timestamp getLastHeartbeatTimestamp()
    {
        return lastHeartbeatTimestamp;
    }

    public void setLastHeartbeatTimestamp(Timestamp lastHeartbeatTimestamp)
    {
        this.lastHeartbeatTimestamp = lastHeartbeatTimestamp;
    }

    public List<Job> getJobs()
    {
        if (jobs == null)
            jobs = new ArrayList<>();

        return jobs;
    }

    public void setJobs(List<Job> jobs)
    {
        this.jobs = jobs;
    }
}
