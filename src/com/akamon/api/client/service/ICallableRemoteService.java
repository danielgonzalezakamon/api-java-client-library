package com.akamon.api.client.service;

/**
 * Represents a remote service 
 * 
 * @author Miguel Angel Garcia
 */
public interface ICallableRemoteService extends ICallableService {
    
    /**
     * Gets the app code (caller id)
     * @return Public client identifier
     */
    public String getAppCode();
    
    /**
     * Gets the app token (caller's secret)
     * @return Private client token
     */
    public String getAppToken();
    
    /**
     * Gets the raw format of the data returned by the invocation call (json, xml, etc)
     * @return Datatype format (xml, json, etc)
     */
    public String getFormat();
}
