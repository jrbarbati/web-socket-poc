package com.personal.batch.hatcher.job.model;

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
}
