package com.akamon.api.client.service;

import java.util.HashMap;

/**
 *
 * @author Miguel Angel Garcia
 */
public interface ICallableServiceFactory {
            
    public ICallableService loadCallableService(String serviceCode, HashMap <String, Object> additionalParams);

}
