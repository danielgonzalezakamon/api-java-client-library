package com.akamon.api.client.service.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class ParameterTooLongException extends BadParameterServiceInvocationException {
    
    private String parameter;
    private Object value;
    
    public ParameterTooLongException(String serviceCode, String parameter, Object value){
        this(serviceCode, parameter, value, null);
    }
    
    public ParameterTooLongException(String serviceCode, String parameter, Object value, Throwable t){
        super("The parameter " + parameter + " value " + (value==null ? "" : value.toString()) + " is too long"  , 
                serviceCode, 
                parameter, 
                value, 
                t);
    }
}