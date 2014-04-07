package com.akamon.api.client.service.error;

import com.akamon.api.client.error.ApiClientException;
import com.akamon.api.client.error.ServiceInvocationException;

/**
 * Encapsultes the error produced reading an invalid service config data
 * @author Miguel Angel Garcia
 */
public class ServiceDefinitionException extends ServiceInvocationException {
 
    /**
     * Builds the object
     * @param serviceCode Operation service code 
     */
    public ServiceDefinitionException(String serviceCode){
        this(serviceCode, null, null);
    }
    
    /**
     * Builds the object
     * @param serviceCode Operation service code 
     * @param t Previous generated error
     */
    public ServiceDefinitionException(String serviceCode, Throwable t){
        this(serviceCode, null, t);
    }
    
    /**
     * Builds the object
     * @param serviceCode Operation service code 
     * @param message Error message
     * @param t Previous generated error
     */
    public ServiceDefinitionException(String serviceCode, String message, Throwable t){
        super("The definition for service " + serviceCode + " is invalid", serviceCode, t);
    }
}
