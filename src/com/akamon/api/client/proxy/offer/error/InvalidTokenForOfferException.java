package com.akamon.api.client.proxy.offer.error;

import com.akamon.api.client.proxy.ServiceProxyException;

public class InvalidTokenForOfferException extends ServiceProxyException {
    
    public InvalidTokenForOfferException(Throwable t){
        super("The token is invalid for this offer", t);
    }
    
    public InvalidTokenForOfferException(){
        super(null);
    }
}
