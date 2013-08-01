package com.akamon.api.client.service;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.RemoteHttpCallableServiceFactory;
import java.util.HashMap;

/**
 *
 * @author Miguel Angel Garcia
 */
public class CallableServiceFactory implements ICallableServiceFactory {
    
    public static final String AUTH_DATA_KEY = "AUTH_DATA";
    
    private AuthData authData = null;
    
    private RemoteHttpCallableServiceFactory httpFactory = null;

    public CallableServiceFactory(HashMap<String,Object> options){
        if ( (options != null) && (options.containsKey(AUTH_DATA_KEY)) ){
           Object oAuthData =  options.get(AUTH_DATA_KEY);
           
           if ( (oAuthData != null) && (oAuthData instanceof AuthData) ){
               this.authData = (AuthData) oAuthData;
           }
        }
        
        httpFactory = new RemoteHttpCallableServiceFactory(this.authData);
    }       
    
    @Override
    public ICallableService loadCallableService(String serviceCode) throws ServiceDefinitionException {         
        return httpFactory.loadCallableService(serviceCode);
    }        
    
}
