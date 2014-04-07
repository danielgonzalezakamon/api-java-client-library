package com.akamon.api.client.error;

/**
 * Exception that encapsulates an error in the client invoking a remote api operation
 * @author Miguel Angel Garcia
 */
public class ApiClientException extends Exception {
    public ApiClientException(String message){
        super(message);
    }
    
    public ApiClientException(String message, Throwable t){
        super(message, t);
    }
}
