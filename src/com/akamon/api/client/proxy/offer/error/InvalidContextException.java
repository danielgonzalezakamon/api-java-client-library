package com.akamon.api.client.proxy.offer.error;

import com.akamon.api.client.proxy.ServiceProxyException;

public class InvalidContextException extends ServiceProxyException {
    
    public InvalidContextException(Throwable t){
        super("The context is invalid", t);
    }
    
    public InvalidContextException(){
        super(null);
    }        
}
