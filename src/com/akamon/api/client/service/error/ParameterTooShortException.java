package com.akamon.api.client.service.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class ParameterTooShortException extends BadParameterServiceInvocationException {       
    
    public ParameterTooShortException(String serviceCode, String parameter, Object value){
        this(serviceCode, parameter, value, null);
    }
    
    public ParameterTooShortException(String serviceCode, String parameter, Object value, Throwable t){
        super("The parameter " + parameter + " value " + (value==null ? "" : value.toString()) + " is too short"  , 
                serviceCode, 
                parameter, 
                value, 
                t);
    }
}
