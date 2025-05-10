package com.personal.batch.hatcher.job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.batch.hatcher.client.model.Client;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Job
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", insertable = false, updatable = false)
    private Long clientId;

    private String name;
    private Integer orgId;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private Timestamp requestedAt;
    private Timestamp startedAt;
    private Timestamp completedAt;
    private String errorMessage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getClientId()
    {
        return clientId;
    }

    public void setClientId(Long clientId)
    {
        this.clientId = clientId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getOrgId()
    {
        return orgId;
    }

    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }

    public JobStatus getStatus()
    {
        return status;
    }

    public void setStatus(JobStatus status)
    {
        this.status = status;
    }

    public Timestamp getRequestedAt()
    {
        return requestedAt;
    }

    public void setRequestedAt(Timestamp requestedAt)
    {
        this.requestedAt = requestedAt;
    }

    public Timestamp getStartedAt()
    {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt)
    {
        this.startedAt = startedAt;
    }

    public Timestamp getCompletedAt()
    {
        return completedAt;
    }

    public void setCompletedAt(Timestamp completedAt)
    {
        this.completedAt = completedAt;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @JsonIgnore
    public Client getClient()
    {
        return client;
    }

    @JsonIgnore
    public void setClient(Client client)
    {
        this.client = client;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private final Job job = new Job();

        public Builder clientId(Long clientId)
        {
            job.setClientId(clientId);
            return this;
        }

        public Builder client(Client client)
        {
            job.setClientId(client.getId());
            job.setClient(client);
            return this;
        }

        public Builder name(String name)
        {
            job.setName(name);
            return this;
        }

        public Builder orgId(Integer orgId)
        {
            job.setOrgId(orgId);
            return this;
        }

        public Builder status(JobStatus status)
        {
            job.setStatus(status);
            return this;
        }

        public Builder requestedAt(Timestamp requestedAt)
        {
            job.setRequestedAt(requestedAt);
            return this;
        }

        public Job build()
        {
            return job;
        }
    }
}
