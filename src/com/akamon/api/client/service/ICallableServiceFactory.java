package com.akamon.api.client.service;

import com.akamon.api.client.service.error.ServiceDefinitionException;

/**
 *
 * @author Miguel Angel Garcia
 */
public interface ICallableServiceFactory {
            
    public ICallableService loadCallableService(String serviceCode) throws ServiceDefinitionException;

}
