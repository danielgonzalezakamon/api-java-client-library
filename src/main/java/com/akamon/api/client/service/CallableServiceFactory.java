package com.akamon.api.client.service;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.RemoteHttpCallableServiceFactory;
import java.util.HashMap;

/**
 * Generic factory to create objects to invoke remote services (proxies). Delegates in
 * RemoteHttpCallableServiceFactory to load the proxy objects
 * @author Miguel Angel Garcia
 */
public class CallableServiceFactory implements ICallableServiceFactory {
    
    public static final String AUTH_DATA_KEY = "AUTH_DATA";
    public static final String URL_PROTOCOL_AND_DOMAIN = "URL_PROTOCOL_AND_DOMAIN";
        
    
    private RemoteHttpCallableServiceFactory httpFactory = null;    

    /**
     * Creates thefactory
     * @param options If a key "AUTH_DATA" exists, it's used for the authentication
     */
    public CallableServiceFactory(HashMap<String,Object> options, java.util.logging.Logger logger){  
        
        AuthData authData = null;
        String urlProtocolAndDomain = null;
        
        boolean customizedOptions = (options != null);
        
        if (customizedOptions){
            authData = loadAuthData(options);
            urlProtocolAndDomain = loadUrlProtocolAndDomain(options);
        }                
        
        httpFactory = new RemoteHttpCallableServiceFactory(urlProtocolAndDomain, authData, logger);
    }      
    
    public CallableServiceFactory(HashMap<String,Object> options){
        this(options, null);
    }
    
    /**
     * Load a callable service
     * @param serviceCode Operation service code
     * @return Callable service object (proxy)
     * @throws ServiceDefinitionException 
     */
    @Override
    public ICallableService loadCallableService(String serviceCode) throws ServiceDefinitionException {         
        return httpFactory.loadCallableService(serviceCode);
    }        
    
    private AuthData loadAuthData(HashMap<String,Object> options){
        
        AuthData authData = null;
        boolean authDataObjectIsPresent = options.containsKey(AUTH_DATA_KEY);
        
        if (!authDataObjectIsPresent) return null;
                
        Object oAuthData =  options.get(AUTH_DATA_KEY);
        boolean authDataObjectIsValid = ( (oAuthData != null) && (oAuthData instanceof AuthData) );
           
        if (authDataObjectIsValid) {
            authData = (AuthData) oAuthData;
        }
        
        return authData;
    }   
    
    private String loadUrlProtocolAndDomain(HashMap<String,Object> options) {
        
        String urlProtocolAndDomain = null;
        
        boolean urlProtocolAndDomainIsPresent = options.containsKey(URL_PROTOCOL_AND_DOMAIN);
        if (!urlProtocolAndDomainIsPresent) return null;
        
        Object oUrlProtocolAndDomain = options.get(URL_PROTOCOL_AND_DOMAIN);
        boolean oUrlProtocolAndDomainIsValid = ((oUrlProtocolAndDomain != null) && (oUrlProtocolAndDomain instanceof String));
        
        if (oUrlProtocolAndDomainIsValid){
            urlProtocolAndDomain = (String) oUrlProtocolAndDomain;
        }        
        
        return urlProtocolAndDomain;
    }
    
}
