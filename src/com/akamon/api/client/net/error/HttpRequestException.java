package com.akamon.api.client.net.error;

import com.akamon.api.client.error.ApiClientException;

/**
 * Encapsulates a generic problem with an http request
 * @author Miguel Angel Garcia
 */
public class HttpRequestException extends ApiClientException {
    /**
     * Builds the exception
     * @param message Error message
     */
    public HttpRequestException(String message){
        super(message);
    }
    
    /**
     * Builds the exception
     * @param message Error message
     * @param t Previous generated error
     */
    public HttpRequestException(String message, Throwable t){
        super(message, t);
    }
}
