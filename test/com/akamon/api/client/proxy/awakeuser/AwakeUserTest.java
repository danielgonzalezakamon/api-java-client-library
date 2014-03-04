package com.akamon.api.client.proxy.awakeuser;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.awakeuser.AwakeUserProxy;
import com.akamon.api.client.proxy.offer.error.InvalidContextException;
import com.akamon.api.client.proxy.offer.error.InvalidTokenForOfferException;
import com.akamon.api.client.proxy.offer.error.OfferEnrolledException;
import com.akamon.api.client.proxy.offer.error.OfferInvalidTokenForUserException;
import com.akamon.api.client.proxy.offer.error.OfferUsedException;
import com.akamon.api.client.test.BaseTest;
import com.akamon.api.client.test.DatabaseManager;
import static org.junit.Assert.*;
import org.junit.Test;

public class AwakeUserTest extends BaseTest {
    @Test
    public void testAwakeUsers(){
        System.out.println("testAwakeUsers");
        
        String errorMessage = ""; 
        AwakeUserProxy proxy = new AwakeUserProxy (createAuthObject());
        
        Exception error = null;
        
        try {
            AwakeUsersResponseData responseData = proxy.awakeUsers("d3d1688b90da4fd93f39a0895fc30c4b", 48);            
        }
        catch (Exception e){
            error = e;
            errorMessage = e.getMessage();
            
            e.printStackTrace();
        }
                                       
        assertNull("error awakeUsers() : " + errorMessage, error);
    }
    
    @Test(expected = InvalidContextException.class)
    public void testConfirmAwakeUser()
            throws ServiceInvocationException, InvalidTokenForOfferException, 
            OfferEnrolledException, OfferInvalidTokenForUserException, 
            InvalidContextException, OfferUsedException
    {
        System.out.println("testConfirmAwakeUser");
        
        AwakeUserProxy proxy = new AwakeUserProxy (createAuthObject());         
        proxy.confirmAwakeUser("d3d1688b90da4fd93f39a0895fc30c4b", "token", 10);        
    }
}
