package com.akamon.api.client.service.error;

/**
 * An exception produced because a parameter valur is too short
 * @author Miguel Angel Garcia
 */
public class ParameterTooShortException extends BadParameterServiceInvocationException {       
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Parameter name
     * @param value  Parameter value
     */
    public ParameterTooShortException(String serviceCode, String parameter, Object value){
        this(serviceCode, parameter, value, null);
    }
    
    /**
     * Builds the exception
     * @param serviceCode  Operation service code
     * @param parameter Parameter name
     * @param value Parameter value
     * @param t Previous generated error
     */
    public ParameterTooShortException(String serviceCode, String parameter, Object value, Throwable t){
        super("The parameter " + parameter + " value " + (value==null ? "" : value.toString()) + " is too short"  , 
                serviceCode, 
                parameter, 
                value, 
                t);
    }
}
