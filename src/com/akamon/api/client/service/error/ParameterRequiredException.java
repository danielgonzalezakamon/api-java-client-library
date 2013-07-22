package com.akamon.api.client.service.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class ParameterRequiredException extends BadParameterServiceInvocationException {
    
    private String parameter;
    
    public ParameterRequiredException(String serviceCode, String parameter){
        this(serviceCode, parameter, null);
    }
    
    public ParameterRequiredException(String serviceCode, String parameter, Throwable t){
        super("The parameter " + parameter + " is required", serviceCode, parameter, null, t);
                
    }
    
}
