package com.akamon.api.client.proxy.offer.error;

import com.akamon.api.client.proxy.ServiceProxyException;

public class OfferUsedException extends ServiceProxyException {
 
    public OfferUsedException(Throwable t){
        super("There was a generic problem with this user and offer", t);
    }
    
    public OfferUsedException(){
        super(null);
    }
    
}
