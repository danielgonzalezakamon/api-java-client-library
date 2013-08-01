package com.akamon.api.client.service;

import org.apache.http.NameValuePair;

/**
 * Interface modeling a callable service
 * @author Miguel Angel Garcia
 */
public interface ICallableService {
    
    /**
     * Get the service code (identifies the operation to call)
     * @return 
     */
    public String getServiceCode();        
    
    
    /**
     * Calls the service
     * @param parameters
     * @return
     */
    public ICallableResponse invoke(NameValuePair[] parameters);    
}
