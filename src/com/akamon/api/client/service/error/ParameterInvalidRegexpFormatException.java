package com.akamon.api.client.service.error;

/**
 * Encapsulates an error produced by a bad parameter value (does not match the defined regular expression)
 * @author Miguel Angel Garcia
 */
public class ParameterInvalidRegexpFormatException extends BadParameterServiceInvocationException {
    
    private String regexp;
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Parameter name
     * @param value Parameter value
     * @param regexp Regular expression
     */
    public ParameterInvalidRegexpFormatException(String serviceCode, String parameter, Object value, String regexp){
        this(serviceCode, parameter, value, regexp, null);
    }
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param parameter Parameter name
     * @param value Parameter value
     * @param regexp Regular expression
     * @param t Previous generated error
     */
    public ParameterInvalidRegexpFormatException(String serviceCode, String parameter, Object value, String regexp, Throwable t){
        super("The parameter '" + parameter + "' with value '" + (value==null ? "" : value.toString()) + "' does not match the regular expression: " + regexp, 
                serviceCode, 
                parameter, 
                value, 
                t);
        
        setRegexp(regexp);
    }

    /**
     * @return the regexp
     */
    public String getRegexp() {
        return regexp;
    }

    /**
     * @param regexp the regexp to set
     */
    private void setRegexp(String regexp) {
        this.regexp = regexp;
    }
}
