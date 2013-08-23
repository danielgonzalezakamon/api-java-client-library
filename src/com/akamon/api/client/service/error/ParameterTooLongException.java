package com.akamon.api.client.service.error;

/**
 * An exception produced because a parameter valur is too long
 * @author Miguel Angel Garcia
 */
public class ParameterTooLongException extends BadParameterServiceInvocationException {
    
    private String parameter;
    private Object value;
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Parameter name
     * @param value  Parameter value
     */
    public ParameterTooLongException(String serviceCode, String parameter, Object value){
        this(serviceCode, parameter, value, null);
    }
    
    /**
     * Builds the exception
     * @param serviceCode  Operation service code
     * @param parameter Parameter name
     * @param value Parameter value
     * @param t Previous generated error
     */
    public ParameterTooLongException(String serviceCode, String parameter, Object value, Throwable t){
        super("The parameter " + parameter + " value " + (value==null ? "" : value.toString()) + " is too long"  , 
                serviceCode, 
                parameter, 
                value, 
                t);
    }
}