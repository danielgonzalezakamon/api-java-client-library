package com.akamon.api.client.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabasePool {
    
    private static DatabaseManager manager = null;
    
    public static DatabaseManager getConnection() throws IOException, ClassNotFoundException{
        if (manager == null){
            manager = createManager();
        }
        
        return manager;
    }
    
    private static DatabaseManager createManager() throws IOException, ClassNotFoundException{       
        Properties dbProperties = loadDatabaseProperties();
        
        String driverName = dbProperties.get("JDBC_CLASS_NAME").toString();
        String url = dbProperties.get("JDBC_URL").toString();
        
        DatabaseManager aManager = new DatabaseManager(driverName, url);
        
        return aManager;
    }
    
    private static Properties loadDatabaseProperties() throws IOException {
        final String databasePropertiesFileName = "test-database.properties";
        Properties dbProperties = new Properties();        
        final InputStream databasePropertiesInputStream = DatabasePool.class.getResourceAsStream(databasePropertiesFileName);
        
        dbProperties.load(databasePropertiesInputStream);
        
        return dbProperties;
    }
}
