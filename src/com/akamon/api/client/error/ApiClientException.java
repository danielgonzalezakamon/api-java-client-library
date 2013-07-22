package com.akamon.api.client.error;

/**
 *
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
