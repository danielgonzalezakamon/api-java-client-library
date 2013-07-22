package com.akamon.api.client.service.error;

import com.akamon.api.client.error.ServiceInvocationException;

/**
 *
 * @author Miguel Angel Garcia
 */
public class BadParameterServiceInvocationException extends ServiceInvocationException {
    
    private String parameter;
    private Object value;
    
    public BadParameterServiceInvocationException(String serviceCode, String parameter, Object value){
        this(null, serviceCode, parameter, value, null);
    }        
    
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
