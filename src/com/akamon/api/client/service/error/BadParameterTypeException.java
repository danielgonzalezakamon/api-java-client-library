package com.akamon.api.client.service.error;

import com.akamon.api.client.error.ServiceInvocationException;

/**
 *
 * @author Miguel Angel Garcia
 */
public class BadParameterTypeException extends BadParameterServiceInvocationException {
        
    private String type;
    
    public BadParameterTypeException(String serviceCode, String parameter, Object value, String type){
        this(serviceCode, parameter, value, type, null);
    }
    
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
