package com.akamon.api.client.service.error;

import com.akamon.api.client.error.ServiceInvocationException;

/**
 * Encapsulates a bad parameter in a service invocation
 * @author Miguel Angel Garcia
 */
public class BadParameterServiceInvocationException extends ServiceInvocationException {
    
    private String parameter;
    private Object value;
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Erroneous parameter
     * @param value Parameter value
     */
    public BadParameterServiceInvocationException(String serviceCode, String parameter, Object value){
        this(null, serviceCode, parameter, value, null);
    }        
    
    /**
     * Builds the exception
     * @param message Error message
     * @param serviceCode Operation service code
     * @param parameter Erroneous parameter
     * @param value Parameter value
     * @param t Previous generated error
     */
    protected BadParameterServiceInvocationException(String message, String serviceCode, String parameter, Object value, Throwable t){
        super(serviceCode, 
                message == null ? ("Bad value " + (value == null ? "null" : value.toString()) + " for parameter " + parameter + " in service " + serviceCode) : message, 
                t);        
        setParameter(parameter);
        setValue(value);
    }

    /**
     * @return the parameter
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * @param parameter the parameter to set
     */
    private void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    private void setValue(Object value) {
        this.value = value;
    }
}
