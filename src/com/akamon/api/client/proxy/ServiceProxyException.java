package com.akamon.api.client.proxy;

import com.akamon.api.client.error.ApiClientException;

public class ServiceProxyException extends ApiClientException {
    public ServiceProxyException(String message){
        super(message);
    }
    
    public ServiceProxyException(String message, Throwable t){
        super(message, t);
    }
    
}
