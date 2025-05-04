package com.personal.batch.hatcher.job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.batch.hatcher.client.model.Client;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "JOB")
@Table(name = "JOB")
public class Job
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_id", insertable = false, updatable = false)
    private UUID clientId;

    private String name;

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

    public UUID getClientId()
    {
        return clientId;
    }

    public void setClientId(UUID clientId)
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
}
