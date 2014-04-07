package com.akamon.api.client.service.error;

import com.akamon.api.client.error.ServiceInvocationException;

/**
 * Encapsulates an error for a bad parameter type in a service invocation
 * @author Miguel Angel Garcia
 */
public class BadParameterTypeException extends BadParameterServiceInvocationException {
        
    private String type;
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Parameter name
     * @param value Parameter value
     * @param type Erroneous parameter type
     */
    public BadParameterTypeException(String serviceCode, String parameter, Object value, String type){
        this(serviceCode, parameter, value, type, null);
    }
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Parameter name
     * @param value Parameter value
     * @param type Erroneous parameter type
     * @param t Previous generated error
     */
    public BadParameterTypeException(String serviceCode, String parameter, Object value, String type, Throwable t){
        super("Bad type " + type + " for parameter " + parameter + " in service " + serviceCode, serviceCode, parameter, value, t);
        
        setType(type);        
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    private void setType(String type) {
        this.type = type;
    }  
    
}
