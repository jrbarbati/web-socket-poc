package com.personal.batch.hatcher.publish.message;

import java.util.UUID;

public class PublishableMessage
{
    private UUID correlationId;

    public UUID getCorrelationId()
    {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId)
    {
        this.correlationId = correlationId;
    }
}
