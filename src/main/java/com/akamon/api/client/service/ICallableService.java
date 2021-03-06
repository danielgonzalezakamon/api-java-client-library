package com.akamon.api.client.service;

import org.apache.http.concurrent.FutureCallback;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.service.error.ServiceDefinitionException;

/**
 * Interface modeling a callable service
 * @author Miguel Angel Garcia
 */
public interface ICallableService {
    
    /**
     * Get the service code (identifies the operation to call)
     * @return Service code (identifies the operation to call)
     */
    public String getServiceCode();        
    
    
    /**
     * Calls the service
     * @param invokationData Invocation parameters
     * @return Encapsulated response obtained from server
     */
    public ICallableResponse invoke(Object[] invokationData) throws ServiceDefinitionException, ServiceInvocationException;

    /**
     * 
     * @param invokationData
     * @param cb
     */
    public void invokeAsync(Object[] invokationData, FutureCallback<ICallableResponse> cb) throws ServiceDefinitionException, ServiceInvocationException;

}
