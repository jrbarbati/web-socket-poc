package com.personal.batch.hatcher.publish.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class BatchRunResponse extends PublishableMessage
{
    private UUID instanceId;
    private String name;
    private Integer orgId;

    @JsonProperty("shouldRun")
    private Boolean shouldRun;

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

    @JsonProperty("shouldRun")
    public Boolean shouldRun()
    {
        return shouldRun;
    }

    @JsonProperty("shouldRun")
    public void setShouldRun(Boolean shouldRun)
    {
        this.shouldRun = shouldRun;
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
