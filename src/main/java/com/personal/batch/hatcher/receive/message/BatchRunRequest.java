package com.personal.batch.hatcher.receive.message;

import java.util.UUID;

public class BatchRunRequest extends ReceivableMessage
{
    private UUID instanceId;
    private String name;
    private int orgId;

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

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private final BatchRunRequest batchRunRequest = new BatchRunRequest();

        public Builder instanceId(UUID instanceId)
        {
            batchRunRequest.setInstanceId(instanceId);
            return this;
        }

        public Builder name(String name)
        {
            batchRunRequest.setName(name);
            return this;
        }

        public Builder orgId(int orgId)
        {
            batchRunRequest.setOrgId(orgId);
            return this;
        }

        public BatchRunRequest build()
        {
            return batchRunRequest;
        }
    }
}
