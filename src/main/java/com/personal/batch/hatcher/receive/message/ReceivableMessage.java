package com.personal.batch.hatcher.receive.message;

import java.util.UUID;

public class ReceivableMessage
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
