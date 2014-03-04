package com.akamon.api.client.proxy;

import com.akamon.api.client.error.ServiceInternalError;
import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.CallableServiceFactory;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.ICallableService;
import com.akamon.api.client.service.ICallableServiceFactory;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Base class to implement the operation proxy objects
 * @author Miguel Angel Garcia
 */
public abstract class BaseServiceProxy  {
    
    private AuthData authData = null;
    
    private ICallableServiceFactory serviceFactory = null;
            
    protected BaseServiceProxy(AuthData authData, java.util.logging.Logger logger){
        this.authData = authData;
        
        HashMap<String,Object> factoryOptions = new LinkedHashMap();
        factoryOptions.put(CallableServiceFactory.AUTH_DATA_KEY, this.authData);
        
        serviceFactory = new CallableServiceFactory(factoryOptions, logger);        
    }
    
    protected BaseServiceProxy(AuthData authData){
        this(authData, null);
    }        
    
    /**
     * Invokes a service
     * @param serviceCode Code of the service to call
     * @param parameters Service invokation parameters
     * @return Response from service
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public ICallableResponse invoke(String serviceCode, Object[] parameters) throws ServiceDefinitionException, ServiceInvocationException {
        ICallableService service = this.serviceFactory.loadCallableService(serviceCode);
        
        ICallableResponse response = service.invoke(parameters);
        
        checkServiceInternalError(serviceCode, response);
        
        return response;
    }            
    
    private void checkServiceInternalError(String serviceCode, ICallableResponse serviceResponse) throws ServiceInternalError{
        final boolean isNotAJsonResponse = ! (serviceResponse instanceof JsonCallableResponse);
        
        if (isNotAJsonResponse) return; 
        
        JsonCallableResponse jsonResponse = (JsonCallableResponse) serviceResponse;
        
        int errorCode = jsonResponse.getErrorCode();
        String errorString = jsonResponse.getErrorString();
        
        final int noErrorCode = 0;
        final boolean anErrorOccurred = (errorCode != noErrorCode);
        
        if (anErrorOccurred){
            throw new ServiceInternalError(serviceCode, errorCode, errorString);
        }
    }
}
