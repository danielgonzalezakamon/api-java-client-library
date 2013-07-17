package com.akamon.api.client.net.error;

/**
 * Encapsulates a generic problem with an http request
 * @author Miguel Angel Garcia
 */
public class HttpRequestException extends Exception {
    public HttpRequestException(String message){
        super(message);
    }
    
    public HttpRequestException(String message, Throwable t){
        super(message, t);
    }
}
