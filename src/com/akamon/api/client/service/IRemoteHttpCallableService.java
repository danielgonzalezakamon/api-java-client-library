package com.akamon.api.client.service;

/**
 * Represents a remote http service 
 * @author Miguel Angel Garcia
 */
public interface IRemoteHttpCallableService extends ICallableRemoteService {
    
    /**
     * Gets the service url
     * @return 
     */
    public String getUrl();
    
    /**
     * Gets the right HTTP method to call the service
     * @return 
     */
    public String getHttpMethod();
}
