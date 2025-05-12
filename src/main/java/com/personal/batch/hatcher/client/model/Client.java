package com.personal.batch.hatcher.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.batch.hatcher.job.model.Job;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID instanceId;

    @Column(updatable = false)
    private Timestamp createdAt;

    private Timestamp lastHeartbeatTimestamp;

    @OneToMany(mappedBy = "client")
    private List<Job> jobs;

    @PrePersist
    public void onCreate()
    {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        this.createdAt = currentTimestamp;

        if (lastHeartbeatTimestamp == null)
            this.lastHeartbeatTimestamp = currentTimestamp;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public UUID getInstanceId()
    {
        return instanceId;
    }

    public void setInstanceId(UUID instanceId)
    {
        this.instanceId = instanceId;
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

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private final Client client = new Client();

        public Builder instanceId(UUID instanceId)
        {
            client.setInstanceId(instanceId);
            return this;
        }

        public Client build()
        {
            return client;
        }
    }
}
