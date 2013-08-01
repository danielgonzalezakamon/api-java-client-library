package com.akamon.api.client.service;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.service.error.ServiceDefinitionException;

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
    public ICallableResponse invoke(Object[] invokationData) throws ServiceDefinitionException, ServiceInvocationException;
}
