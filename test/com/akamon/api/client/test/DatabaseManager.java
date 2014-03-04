
package com.akamon.api.client.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseManager {
           
    private String jdbcUrl;
    
    private Connection conn;
    
    DatabaseManager(String driverName, String jdbcUrl) throws ClassNotFoundException{
        Class.forName(driverName);
        this.jdbcUrl = jdbcUrl;
    }
             
    public void execute(String sql) throws SQLException{
        Connection db = getConnection();
        
        Statement st = db.createStatement();
        st.executeUpdate(sql);
    }
    
    public List<HashMap> select(String sql) throws SQLException{
        Connection db = getConnection();
        Statement st = db.createStatement();
        
        List<HashMap> results = new ArrayList<HashMap>();
        
        ResultSet rs = st.executeQuery(sql);
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        
        while (rs.next()){
            HashMap row = new HashMap(columns);
            
            for (int i = 1; i <= columns; i++){           
                row.put(md.getColumnName(i),rs.getObject(i));
            }
            
            results.add(row);
        }
        
        return results;
    }
    
    public void disconnect() throws SQLException{
        if (this.conn != null) {
            this.conn.close();
        }
    }
    
    private Connection getConnection() throws SQLException{
        if ( conn == null ){
            connectToDatabase();
        }
        
        return conn;
    }        
    
    private void connectToDatabase() throws SQLException{
        this.conn = DriverManager.getConnection(jdbcUrl);        
    } 
}
