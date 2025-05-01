package com.personal.batch.hatcher.publish.message;

import java.util.UUID;

public class BatchRunResponse extends PublishableMessage
{
    private UUID instanceId;
    private String name;
    private int orgId;
    private boolean shouldRun;

    public UUID getInstanceId()
    {
        return instanceId;
    }

    public void setInstanceId(UUID instanceId)
    {
        this.instanceId = instanceId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getOrgId()
    {
        return orgId;
    }

    public void setOrgId(int orgId)
    {
        this.orgId = orgId;
    }

    public boolean shouldRun()
    {
        return shouldRun;
    }

    public void setShouldRun(boolean shouldRun)
    {
        this.shouldRun = shouldRun;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private final BatchRunResponse message = new BatchRunResponse();

        public Builder instanceId(UUID instanceId)
        {
            message.setInstanceId(instanceId);
            return this;
        }

        public Builder name(String name)
        {
            message.setName(name);
            return this;
        }

        public Builder orgId(int orgId)
        {
            message.setOrgId(orgId);
            return this;
        }

        public Builder shouldRun(boolean shouldRun)
        {
            message.setShouldRun(shouldRun);
            return this;
        }

        public Builder correlationId(UUID correlationId)
        {
            message.setCorrelationId(correlationId);
            return this;
        }

        public BatchRunResponse build()
        {
            return message;
        }
    }
}
