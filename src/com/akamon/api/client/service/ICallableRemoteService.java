package com.akamon.api.client.service;

/**
 * Represents a remote service 
 * 
 * @author Miguel Angel Garcia
 */
public interface ICallableRemoteService extends ICallableService {
    
    /**
     * Gets the app code (caller id)
     * @return 
     */
    public String getAppCode();
    
    /**
     * Gets the app token (caller's secret)
     * @return 
     */
    public String getAppToken();
}
