/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akamon.api.client.service.error;

import com.akamon.api.client.error.BadResponseInvocationException;

/**
 *
 * @author Miguel Angel Garcia
 */
public class BadHttpResponseInvocationException extends BadResponseInvocationException
{
    private int httpResponseCode = 0;
    
    public BadHttpResponseInvocationException(String serviceCode, String rawResponse, int httpResponseCode){
        this(serviceCode, rawResponse, httpResponseCode, null);
    }
    
    public BadHttpResponseInvocationException(String serviceCode, String rawResponse, int httpResponseCode, Throwable t){
        super(serviceCode, rawResponse, t);
        
        setHttpResponseCode(httpResponseCode);
    }

    /**
     * @return the httpResponseCode
     */
    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    /**
     * @param httpResponseCode the httpResponseCode to set
     */
    private void setHttpResponseCode(int httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }
    
}
