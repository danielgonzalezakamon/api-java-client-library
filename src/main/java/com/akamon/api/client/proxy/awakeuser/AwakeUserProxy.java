package com.akamon.api.client.proxy.awakeuser;

import com.akamon.api.client.error.ServiceInternalError;
import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.proxy.ErrorCodes;
import com.akamon.api.client.proxy.offer.error.InvalidContextException;
import com.akamon.api.client.proxy.offer.error.InvalidTokenForOfferException;
import com.akamon.api.client.proxy.offer.error.OfferEnrolledException;
import com.akamon.api.client.proxy.offer.error.OfferInvalidTokenForUserException;
import com.akamon.api.client.proxy.offer.error.OfferUsedException;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.error.BadHttpResponseInvocationException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;
import com.google.gson.JsonParseException;
import java.util.logging.Logger;


public class AwakeUserProxy extends BaseServiceProxy {
    
    public AwakeUserProxy(String serviceUrlProtocolAndDomain, AuthData authData, Logger logger) {
        super(serviceUrlProtocolAndDomain, authData, logger);
    }
    
    public AwakeUserProxy(String serviceUrlProtocolAndDomain, AuthData authData){
        this(serviceUrlProtocolAndDomain, authData, null);
    }
    
    public AwakeUsersResponseData awakeUsers(String publicUserId, int gameId) throws ServiceInvocationException {       
        AwakeUsersResponseData response = null;
        Object[] params = {publicUserId, gameId};
        
        ICallableResponse rawResponse = invoke("awakeUsers", params);
        
        if (rawResponse instanceof JsonCallableResponse ){
            JsonCallableResponse jsonResponse = (JsonCallableResponse) rawResponse;
            
            try {
                response = jsonResponse.buildResponseDataObject(AwakeUsersResponseData.class);
                if (response.getOffer() == null){
                    response = null;
                }
            }
            catch (JsonParseException ex){
                 throw new ServiceInvocationException("awakeUsers", "Response parse error", ex);
            }
        }
        
        return response;
    }      
    
    public ConfirmAwakeUserResponseData confirmAwakeUser(String publicUserId, String token, Integer contextId) 
            throws ServiceInvocationException, InvalidTokenForOfferException, 
            OfferEnrolledException, OfferInvalidTokenForUserException, 
            InvalidContextException, OfferUsedException
    {        
        ConfirmAwakeUserResponseData response = null;
        
        try {            
            Object[] params = {publicUserId, token, contextId};
            ICallableResponse rawResponse = invoke("confirmAwakeUsers", params);

            if (rawResponse instanceof JsonCallableResponse ){
                JsonCallableResponse jsonResponse = (JsonCallableResponse) rawResponse;

                try {
                    response = jsonResponse.buildResponseDataObject(ConfirmAwakeUserResponseData.class);
                }
                catch (JsonParseException ex){
                     throw new ServiceInvocationException("confirmAwakeUsers", "Response parse error", ex);
                }                        
            }
            
        }
        catch (ServiceInternalError sie){            
            translateToSpecificOfferDomainException(sie.getServiceErrorCode());
            
            throw sie;
        }
        catch (BadHttpResponseInvocationException bre){            
            translateToSpecificOfferDomainException(bre.getServiceErrorCode());
            
            throw bre;            
        }
        
        return response;
    }
            
    private void translateToSpecificOfferDomainException(int errorCode) 
            throws InvalidTokenForOfferException, OfferEnrolledException, 
            OfferInvalidTokenForUserException, InvalidContextException, 
            OfferUsedException
    {        
        switch (errorCode) {
            case ErrorCodes.OFFER_INVALID_TOKEN:
                throw new InvalidTokenForOfferException();
                
            case ErrorCodes.OFFER_ENROLLED_ERROR:
                throw new OfferEnrolledException();
                
            case ErrorCodes.OFFER_INVALID_USER_FOR_TOKEN:
                throw new OfferInvalidTokenForUserException();
                
            case ErrorCodes.CONTEXT_INVALID:
                throw new InvalidContextException();
                
            case ErrorCodes.OFFER_USED:
                throw new OfferUsedException();                
        }
    }
}
