/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akamon.api.client.net.error;

/**
 *
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
