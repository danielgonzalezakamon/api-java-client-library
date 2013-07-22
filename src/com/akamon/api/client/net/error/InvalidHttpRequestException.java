package com.akamon.api.client.net.error;

/**
 * Encapsulates an ivalid http request error
 * @author Miguel Angel Garcia
 */
public class InvalidHttpRequestException extends HttpRequestException  {
    public InvalidHttpRequestException(String message){
        super(message);
    }
    
    public InvalidHttpRequestException(String message, Throwable t){
        super(message, t);
    }
}
