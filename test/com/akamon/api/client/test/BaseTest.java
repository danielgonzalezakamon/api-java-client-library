package com.akamon.api.client.test;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ServiceConfigManager;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic class to extend for every test 
 * 
 */
public class BaseTest {
    
    private static DatabaseManager databaseManager = null;                        
    
    /**
     * Creates an auth object required to connect with the api server
     * @return auth object
     */
    protected AuthData createAuthObject ()
    {
        return new AuthData("app1","7535866746d65a7eef0241caa8163fe7","sha256");
    }
    
    protected String getDefaultTestServiceUrlProtocolAndDomain()
    {
        return "http://dev-api.mundijuegos.servidor/app_dev.php";
    }
    
   @Test
    public void testOK() {
        assertTrue (true);
    }        
    
    @AfterClass 
    public static void closeDatabaseConnection () throws SQLException {
        if (databaseManager != null){
            databaseManager.disconnect();
        }
    }
    
    protected DatabaseManager getDatabaseManager() throws IOException, ClassNotFoundException {
        
        if (databaseManager == null){
            databaseManager = DatabasePool.getConnection();
        }
        
        return databaseManager;
    }
}
