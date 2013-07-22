package com.akamon.api.client.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class BadResponseInvocationException extends ServiceInvocationException {
    private Object rawResponse;
    
    
    public BadResponseInvocationException(String serviceCode, Object rawResponse){
        super("Bad response obtained from service " + serviceCode + ": " + (rawResponse == null ? "" : rawResponse.toString()));
        this.rawResponse = rawResponse;
    }

    /**
     * @return the rawResponse
     */
    public Object getRawResponse() {
        return rawResponse;
    }
}
