package com.akamon.api.client.proxy.offer.error;

import com.akamon.api.client.proxy.ServiceProxyException;

public class OfferEnrolledException extends ServiceProxyException {
    
    public OfferEnrolledException(Throwable t){
        super("The user could not be enrolled to  the offer", t);
    }
    
    public OfferEnrolledException(){
        super(null);
    }

}
