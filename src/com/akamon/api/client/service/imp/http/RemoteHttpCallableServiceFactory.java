/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akamon.api.client.service.imp.http;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableService;
import com.akamon.api.client.service.ICallableServiceFactory;
import com.akamon.api.client.service.error.ServiceDefinitionException;

/**
 *
 * @author Miguel Angel Garcia
 */
public class RemoteHttpCallableServiceFactory implements ICallableServiceFactory {
    
    private AuthData authData;
    
    public RemoteHttpCallableServiceFactory(AuthData authData){
        this.authData = authData;
    }

    @Override
    public ICallableService loadCallableService(String serviceCode) throws ServiceDefinitionException {        
        return new RemoteHttpCallableService(serviceCode, this.authData);        
    }
    
}
