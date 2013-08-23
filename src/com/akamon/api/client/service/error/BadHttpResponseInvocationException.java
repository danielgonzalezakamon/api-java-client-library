package com.akamon.api.client.service.error;

import com.akamon.api.client.error.BadResponseInvocationException;

/**
 * Encapsulates an error for a bad http response obtained from server
 * @author Miguel Angel Garcia
 */
public class BadHttpResponseInvocationException extends BadResponseInvocationException
{
    private int httpResponseCode = 0;
    
    /**
     * Buils the exception
     * @param serviceCode Operation service code
     * @param rawResponse Raw response obtained from server
     * @param httpResponseCode http response code
     */
    public BadHttpResponseInvocationException(String serviceCode, String rawResponse, int httpResponseCode){
        this(serviceCode, rawResponse, httpResponseCode, null);
    }
    
    /**
     * Buils the exception
     * @param serviceCode Operation service code
     * @param rawResponse Raw response obtained from server
     * @param httpResponseCode http response code
     * @param t Previous generated error
     */
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
