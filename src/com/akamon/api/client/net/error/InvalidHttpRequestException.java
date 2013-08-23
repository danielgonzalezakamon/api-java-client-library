package com.akamon.api.client.net.error;

/**
 * Encapsulates an ivalid http request error
 * @author Miguel Angel Garcia
 */
public class InvalidHttpRequestException extends HttpRequestException  {
    /**
     * Builds the exception
     * @param message Error message 
     */
    public InvalidHttpRequestException(String message){
        super(message);
    }
    
    /**
     * Builds the exception
     * @param message Error message
     * @param t Previous generated error
     */
    public InvalidHttpRequestException(String message, Throwable t){
        super(message, t);
    }
}
