package com.akamon.api.client.service.imp.http.error;

import com.akamon.api.client.error.BadResponseInvocationException;

/**
 * Error encapsulating a bad json response obtained from server
 * @author Miguel Angel Garcia
 */
public class BadJsonResponseInvocationException extends BadResponseInvocationException {
     
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param jsonResponse Json response obtained from server
     */
    public BadJsonResponseInvocationException(String serviceCode, String jsonResponse){
        super(serviceCode, jsonResponse);
    }     
}
