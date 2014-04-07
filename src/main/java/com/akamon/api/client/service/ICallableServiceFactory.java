package com.akamon.api.client.service;

import com.akamon.api.client.service.error.ServiceDefinitionException;

/**
 * Interface to model the loading of the callable service objects (proxies)
 * @author Miguel Angel Garcia
 */
public interface ICallableServiceFactory {
    /**
     * Loads a callable service object (proxy)
     * @param serviceCode Operation service code
     * @return Callable service object (proxy)
     * @throws ServiceDefinitionException 
     */
    public ICallableService loadCallableService(String serviceCode) throws ServiceDefinitionException;

}
