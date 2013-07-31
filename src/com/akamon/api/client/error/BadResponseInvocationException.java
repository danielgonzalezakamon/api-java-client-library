package com.akamon.api.client.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class BadResponseInvocationException extends ServiceInvocationException {
    private Object rawResponse;
    
    public BadResponseInvocationException(String serviceCode, Object rawResponse){
        this(serviceCode, rawResponse, null);        
    }
    
    public BadResponseInvocationException(String serviceCode, Object rawResponse, Throwable t){
        super("Bad response obtained from service " + serviceCode + ": " + (rawResponse == null ? "" : rawResponse.toString()), t);
        this.rawResponse = rawResponse;
    }

    /**
     * @return the rawResponse
     */
    public Object getRawResponse() {
        return rawResponse;
    }
}
