package com.akamon.api.client.net.error;

/**
 * Encapsulates an error produces by the use of an invalid http method
 * @author Miguel Angel Garcia
 */
public class InvalidHttpMethodException extends InvalidHttpRequestException {
    
    private String method;
    
    /**
     * Builds the exception
     * @param method Invalid http method
     */
    public InvalidHttpMethodException(String method){                
        super("Invalid http method " + method);
        
        this.method = method;
    }
    
    /**
     * Gets the http method
     * @return Http method
     */
    public String getMethod(){
        return method;
    }
}
