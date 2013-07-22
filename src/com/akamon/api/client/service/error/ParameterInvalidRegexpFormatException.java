package com.akamon.api.client.service.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class ParameterInvalidRegexpFormatException extends BadParameterServiceInvocationException {
    
    private String regexp;
        
    public ParameterInvalidRegexpFormatException(String serviceCode, String parameter, Object value, String regexp){
        this(serviceCode, parameter, value, regexp, null);
    }
    
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
