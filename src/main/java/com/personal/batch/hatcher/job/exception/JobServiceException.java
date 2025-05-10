package com.personal.batch.hatcher.job.exception;

public class JobServiceException extends Exception
{
    public JobServiceException(String message)
    {
        this(message, null);
    }

    public JobServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
