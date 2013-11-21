package com.akamon.api.client.proxy.product;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ServiceConfigManager;
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
public class ProductProxyTest {
    
    public ProductProxyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setProductTransaction method, of class ProductProxy.
     */
    @Test
    public void testSetProductTransaction() throws Exception {
        System.out.println ("setProductTransaction test");
        
        ServiceConfigManager.registerConfigDir("./service_definitions");

        ProductProxy p = new ProductProxy(new AuthData("app_code","app_token","sha256"));
        
        //TODO Change with appropiate values to test
        Integer productId = 0;
        Integer productQuantity = 0;
        Integer transactionTypeId = 0;
        String publicUserId = "";
        Integer countryId = 0;
        Integer partnerId = 0;
        Integer subplatformId = 0;
        Integer gameId = 0;

        Exception ex = null;
        ProductTransactionResponseData res = null;
        try {
            res = p.setProductTransaction (productId,
                productQuantity,
                transactionTypeId,
                publicUserId,
                countryId,
                partnerId,
                subplatformId,
                gameId);
        }
        catch (Exception e) {
            ex = e;
            
            e.printStackTrace();
        }

        assertTrue("An exception was returned", ex == null);
    }
}
