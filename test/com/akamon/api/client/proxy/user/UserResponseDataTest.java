package com.akamon.api.client.proxy.user;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author miguelgarcia
 */
public class UserResponseDataTest {
    
    @Test
    public void testGetFacebookId()
    {
        String expectedFacebookUserId = "10";
        
        UserResponseData responseData = new UserResponseData();
        List <UserLinkFromProviderResponseData> linKFromProviders = 
                createUserLinkFromProvider(UserResponseData.PROVIDER_FACEBOOK, expectedFacebookUserId);        
        responseData.setLinks_from_providers(linKFromProviders);
        
        String obtainedFacebookUserId = responseData.getFacebookUserId();
        
        assertEquals("Facebook user does not match", expectedFacebookUserId, obtainedFacebookUserId);
    }
    
    @Test
    public void testGetFacebookIdWhenEmptyProviderInfoData()
    {
        String expectedFacebookUserId = null;
        
        UserResponseData responseData = new UserResponseData();
        List <UserLinkFromProviderResponseData> linKFromProviders = new ArrayList();
        responseData.setLinks_from_providers(linKFromProviders);
        
        String obtainedFacebookUserId = responseData.getFacebookUserId();
        
        assertEquals("Facebook user not null", expectedFacebookUserId, obtainedFacebookUserId);
    }
            
    private List <UserLinkFromProviderResponseData>  createUserLinkFromProvider(String providerName, String providerUserId)
    {
        List <UserLinkFromProviderResponseData> linKFromProviders = new ArrayList();
        UserLinkFromProviderResponseData providerData = new UserLinkFromProviderResponseData();
        providerData.setProvider(providerName);
        providerData.setProvider_user_id(providerUserId);        
        
        linKFromProviders.add(providerData);
        
        return linKFromProviders;        
    }
    
}
