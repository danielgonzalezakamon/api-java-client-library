package com.akamon.api.client.service.imp.http;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableService;
import com.akamon.api.client.service.ICallableServiceFactory;
import com.akamon.api.client.service.error.ServiceDefinitionException;

/**
 * Factory to create valid proxy objects to call the services via the http invocation
 * @author Miguel Angel Garcia
 */
public class RemoteHttpCallableServiceFactory implements ICallableServiceFactory {
    
    private AuthData authData;
    
    /**
     * Builds the factory
     * @param authData Authentication data object
     */
    public RemoteHttpCallableServiceFactory(AuthData authData){
        this.authData = authData;
    }

    /**
     * Loads a proxy object to call the service code serviceCode
     * @param serviceCode Operation service code
     * @return
     * @throws ServiceDefinitionException 
     */
    @Override
    public ICallableService loadCallableService(String serviceCode) throws ServiceDefinitionException {        
        return new RemoteHttpCallableService(serviceCode, this.authData);        
    }
    
}
