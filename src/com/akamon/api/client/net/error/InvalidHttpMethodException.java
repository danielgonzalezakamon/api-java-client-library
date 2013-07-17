package com.akamon.api.client.net.error;

/**
 * Encapsulates an error produces by the use of an invalid http method
 * @author Miguel Angel Garcia
 */
public class InvalidHttpMethodException extends InvalidHttpRequestException {
    
    private String method;
    
    public InvalidHttpMethodException(String method){                
        super("Invalid http method " + method);
        
        this.method = method;
    }
    
    public String getMethod(){
        return method;
    }
}
