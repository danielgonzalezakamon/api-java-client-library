package com.akamon.api.client.service.error;

import com.akamon.api.client.error.ApiClientException;
import com.akamon.api.client.error.ServiceInvocationException;

/**
 *
 * @author Miguel Angel Garcia
 */
public class ServiceDefinitionException extends ServiceInvocationException {
    
    public ServiceDefinitionException(String serviceCode){
        this(serviceCode, null, null);
    }
    
    public ServiceDefinitionException(String serviceCode, Throwable t){
        this(serviceCode, null, t);
    }
    
    public ServiceDefinitionException(String serviceCode, String message, Throwable t){
        super("The definition for service " + serviceCode + " is invalid", serviceCode, t);
    }
}
