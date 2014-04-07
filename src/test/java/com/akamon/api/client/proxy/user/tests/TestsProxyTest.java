package com.akamon.api.client.proxy.user.tests;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.tests.SayHelloResponseData;
import com.akamon.api.client.proxy.tests.TestsProxy;
import com.akamon.api.client.test.BaseTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestsProxyTest extends BaseTest {
    
    @Test
    public void testSayHello() throws ServiceInvocationException {
        System.out.println("sayHello");
        
        String aUserName = "Peter";
        
        String protocolUrlAndDomain = getDefaultTestServiceUrlProtocolAndDomain();        
        TestsProxy proxy = new TestsProxy (protocolUrlAndDomain, createAuthObject());
        
        SayHelloResponseData greeting = proxy.sayHello(aUserName);
        
        String expectedGreeting = "Hello " + aUserName;
        String obtainedGreeting = greeting.getGreeting();
        
        assertEquals(expectedGreeting, obtainedGreeting);               
    }            
}
