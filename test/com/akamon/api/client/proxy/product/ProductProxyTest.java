package com.akamon.api.client.proxy.product;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ServiceConfigManager;
import java.util.ArrayList;
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
    public void testSetProductTransaction() {
        System.out.println ("setProductTransaction test");
        ServiceConfigManager.registerConfigDir("C:\\Users\\Isis\\Documents\\GitHub\\api-java-client-library\\service_definitions"); //"./service_definitions"

        ProductProxy p = new ProductProxy(new AuthData("app1","7535866746d65a7eef0241caa8163fe7","sha256"));
        
        Integer productId = 3; //roulette_empires_gems_pack1
        Integer productQuantity = 1;
        Integer transactionTypeId = 1; //payment
        String publicUserId = "dc03056da75009ffe0e83863c0deef27";
        Integer countryId = 172;
        Integer partnerId = 1;
        Integer subplatformId = 1;
        Integer gameId = 1;

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
    
    /**
     * Test of setProductTransaction method, of class ProductProxy.
     */
    @Test
    public void testSetProductExchangeTransaction() {
        System.out.println ("setProductExchangeTransaction test");
        ServiceConfigManager.registerConfigDir("C:\\Users\\Isis\\Documents\\GitHub\\api-java-client-library\\service_definitions"); //"./service_definitions"

        ProductProxy p = new ProductProxy(new AuthData("app1","7535866746d65a7eef0241caa8163fe7","sha256"));
        
        Integer productId = 3; //roulette_empires_gems_pack1
        Integer productQuantity = 1;
        Integer exchangeProductId = 1; //chips
        Integer exchangeQuantity = -5;
        Integer transactionTypeId = 1; //payment
        String publicUserId = "dc03056da75009ffe0e83863c0deef27";
        Integer countryId = 172;
        Integer partnerId = 1;
        Integer subplatformId = 1;
        Integer gameId = 1;

        Exception ex = null;
        ProductExchangeTransactionResponseData res = null;
        try {
            res = p.setProductExchangeTransaction (productId,
                productQuantity,
                exchangeProductId,
                exchangeQuantity,
                transactionTypeId,
                publicUserId,
                countryId,
                partnerId,
                subplatformId,
                gameId);
            System.out.println(res);
        }
        catch (Exception e) {
            ex = e;
            
            e.printStackTrace();
        }

        assertTrue("An exception was returned", ex == null);
    }
    
    /**
     * Test of getGameProductsResponseData method, of class ProductProxy.
     */
    @Test
    public void testGetGameProductsResponseData() {
        System.out.println ("getGameProductsResponseData test");
        ServiceConfigManager.registerConfigDir("C:\\Users\\Isis\\Documents\\GitHub\\api-java-client-library\\service_definitions"); //"./service_definitions"

        ProductProxy p = new ProductProxy(new AuthData("app1","7535866746d65a7eef0241caa8163fe7","sha256"));
        
        Integer gameId = 46; //roulette empires

        Exception ex = null;
        ArrayList res = null;
        try {
            res = p.getGameProducts (gameId);
        }
        catch (Exception e) {
            ex = e;
            
            e.printStackTrace();
        }

        assertTrue("An exception was returned", ex == null);
    }
}
