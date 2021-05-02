package com.quarksy.error;

import lombok.Data;

import javax.ws.rs.core.Response;

@Data
public class ErrorEntity
{
    private String errorType;
    private String message;

    public ErrorEntity()
    {
        this.errorType = Response.Status.fromStatusCode(500).toString();
        this.message = "An internal error has occurred.";
    }

    public ErrorEntity(int status, String message)
    {
        this.errorType = Response.Status.fromStatusCode(status).toString();
        this.message = message;
    }

}
