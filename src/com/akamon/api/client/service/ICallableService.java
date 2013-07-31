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
     * Gets the raw format of the data returned by the invocation call (json, xml, etc)
     * @return 
     */
    public String getFormat();
    
    /**
     * Validates the service parameters
     * @param parameters
     * @return 
     */
    public boolean validateServiceParameters(NameValuePair[] parameters);
    
    /**
     * Calls the service
     * @param parameters
     * @return
     */
    public ICallableResponse invoke(NameValuePair[] parameters);    
}
