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
    private String name;

    @Column(updatable = false)
    private Timestamp createdAt;

    private Timestamp updatedAt;
    private Boolean active;
    private Timestamp lastHeartbeatTimestamp;

    @OneToMany(mappedBy = "client")
    private List<Job> jobs;

    @PrePersist
    public void onCreate()
    {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    @PreUpdate
    public void onUpdate()
    {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Timestamp getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt)
    {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive()
    {
        if (active == null)
            return false;

        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
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
