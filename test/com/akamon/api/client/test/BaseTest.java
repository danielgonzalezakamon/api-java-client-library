package com.akamon.api.client.test;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ServiceConfigManager;

/**
 * Basic class to extend for every test 
 * 
 * @author Miguel Angel Garcia
 */
public abstract class BaseTest {
    
    protected BaseTest ()
    {
         ServiceConfigManager.registerConfigDir("service_definitions");
    }
    
    /**
     * Creates an auth object required to connect with the api server
     * @return auth object
     */
    protected AuthData createAuthObject ()
    {
        return new AuthData("app1","7535866746d65a7eef0241caa8163fe7","sha256");
    }
}
