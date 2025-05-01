package com.personal.batch.hatcher.receive.message;

import java.util.UUID;

public class Heartbeat extends ReceivableMessage
{
    private UUID instanceId;

    public UUID getInstanceId()
    {
        return instanceId;
    }

    public void setInstanceId(UUID instanceId)
    {
        this.instanceId = instanceId;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private final Heartbeat message = new Heartbeat();

        public Builder instanceId(UUID instanceId)
        {
            message.setInstanceId(instanceId);
            return this;
        }

        public Heartbeat build()
        {
            return message;
        }
    }
}
