package com.akamon.api.client.proxy.awakeuser;

import com.akamon.api.client.proxy.awakeuser.AwakeUserProxy;
import com.akamon.api.client.test.BaseTest;
import static org.junit.Assert.*;
import org.junit.Test;

public class AwakeUserTest extends BaseTest {
    @Test
    public void testAwakeUsers(){
        System.out.println("testAwakeUsers");
        
        AwakeUserProxy proxy = new AwakeUserProxy (createAuthObject());
        Exception error = null;
        
        try {
            proxy.awakeUsers("d3d1688b90da4fd93f39a0895fc30c4b", 48);
        }
        catch (Exception e){
            error = e;
            
            e.printStackTrace();
        }
        
        assertNull("there was an error invocating awakeUsers()", error);
    }
}
