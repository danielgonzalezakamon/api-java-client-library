package com.akamon.api.client.service.error;

import com.akamon.api.client.error.BadResponseInvocationException;

/**
 * Encapsulates an error for a bad http response obtained from server
 * @author Miguel Angel Garcia
 */
public class BadHttpResponseInvocationException extends BadResponseInvocationException
{
    private int httpResponseCode = 0;
    
    private String httpResponseFormat;
    
    private int serviceErrorCode;
    
    private String serviceErrorString; 
    
    /**
     * Buils the exception
     * @param serviceCode Operation service code
     * @param rawResponse Raw response obtained from server
     * @param httpResponseCode http response code
     */    
    public BadHttpResponseInvocationException(HttpServiceResponseData httpServiceResponseData){
        this(httpServiceResponseData, null);
    }
    
    /**
     * Buils the exception
     * @param serviceCode Operation service code
     * @param rawResponse Raw response obtained from server
     * @param httpResponseCode http response code
     * @param t Previous generated error
     */
    public BadHttpResponseInvocationException(HttpServiceResponseData httpServiceResponseData, Throwable t){
        super(httpServiceResponseData.getServiceCode(), httpServiceResponseData.getHttpResponseBody(), t);
        
        httpResponseCode = httpServiceResponseData.getHttpResponseCode();
        httpResponseFormat = httpServiceResponseData.getHttpResponseFormat();
        serviceErrorCode = httpServiceResponseData.getServiceErrorCode();
        serviceErrorString = httpServiceResponseData.getServiceErrorString();
    }

    /**
     * @return the httpResponseCode
     */
    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    /**
     * @return the httpResponseFormat
     */
    public String getHttpResponseFormat() {
        return httpResponseFormat;
    }

    /**
     * @return the serviceErrorCode
     */
    public int getServiceErrorCode() {
        return serviceErrorCode;
    }

    /**
     * @return the serviceErrorString
     */
    public String getServiceErrorString() {
        return serviceErrorString;
    }       
    
}
