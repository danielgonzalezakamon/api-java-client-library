package com.akamon.api.client.proxy.offer.error;

import com.akamon.api.client.proxy.ServiceProxyException;

public class OfferInvalidTokenForUserException extends ServiceProxyException {

    public OfferInvalidTokenForUserException(Throwable t){
        super("The token does not belong to the current user", t);
    }
    
    public OfferInvalidTokenForUserException(){
        super(null);
    }
    
}
