package com.akamon.api.client.error;

/**
 * Encapsulates a bad response obtained from the server
 * @author Miguel Angel Garcia
 */
public class BadResponseInvocationException extends ServiceInvocationException {
    private Object rawResponse;
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param rawResponse Bad response obtained from the server
     */
    public BadResponseInvocationException(String serviceCode, Object rawResponse){
        this(serviceCode, rawResponse, null);        
    }
    
    /**
     * Builds the exception
     * @param serviceCode Operation servicecode
     * @param rawResponse Bad response obtained from the server
     * @param t Previous generated error
     */
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
