package com.personal.batch.hatcher.receive.message;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class BatchRunRequest extends ReceivableMessage
{
    private UUID instanceId;
    private String name;
    private Integer orgId;

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

    public Integer getOrgId()
    {
        return orgId;
    }

    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }

    @Override
    public String toString()
    {
        try
        {
            return new ObjectMapper().writeValueAsString(this);
        }
        catch (Exception e)
        {
            return "Caught %s: %s".formatted(e.getClass().getSimpleName(), e.getMessage());
        }
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

        public Builder orgId(Integer orgId)
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
