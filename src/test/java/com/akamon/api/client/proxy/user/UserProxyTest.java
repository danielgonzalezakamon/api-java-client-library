package com.akamon.api.client.proxy.user;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ServiceConfigManager;
import com.akamon.api.client.test.BaseTest;
import java.util.HashMap;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia
 */
public class UserProxyTest extends BaseTest {
            
    /**
     * Test of createUserSession method, of class UserProxy.
     */
    @Test
    public void testCreateUserSession() throws Exception {
        System.out.println("createUserSession");
        
        Logger logger = Logger.getLogger(this.getClass().getName());
        
        String protocolUrlAndDomain = getDefaultTestServiceUrlProtocolAndDomain();  
        UserProxy proxy = new UserProxy (protocolUrlAndDomain, createAuthObject(), logger);
        UserSessionResponseData session = proxy.createUserSession ("carles");
        
        assertNotNull("No session object obtained from server", session);
        
        if (session != null) {
            assertNotNull("No session obtained from server", session.getSession_id());
            
            if ( session.getSession_id() != null ) {
                assertTrue ("The session string is empty", session.getSession_id().length() > 0);
            }
        }                
    }

    /**
     * Test of userSessionGetContent method, of class UserProxy.
     */
    @Test
    public void testUserSessionGetContent() throws Exception {
        
        System.out.println("userSessionGetContent");
        
        String protocolUrlAndDomain = getDefaultTestServiceUrlProtocolAndDomain();  
        UserProxy proxy = new UserProxy (protocolUrlAndDomain, createAuthObject());
        UserSessionResponseData session = proxy.createUserSession ("carles");
        
        UserResponseData userData = proxy.userSessionGetContent(session.getSession_id());
        
        assertNotNull("No user data object obtained from server", userData);
        
        if ( userData != null ) {
            assertEquals ("The returned user does not match the expected", "carles", userData.getUser_name());                        
        }
    }                

}
