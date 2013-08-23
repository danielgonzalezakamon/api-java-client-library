package com.akamon.api.client.service.error;

/**
 * An exception produced by the abscence of a required parameter
 * @author Miguel Angel Garcia
 */
public class ParameterRequiredException extends BadParameterServiceInvocationException {
    
    private String parameter;
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Parameter name
     */
    public ParameterRequiredException(String serviceCode, String parameter){
        this(serviceCode, parameter, null);
    }
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Parameter name
     * @param t Previous generated error
     */
    public ParameterRequiredException(String serviceCode, String parameter, Throwable t){
        super("The parameter " + parameter + " is required", serviceCode, parameter, null, t);
                
    }
    
}
