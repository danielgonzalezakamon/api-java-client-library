package com.akamon.api.client.proxy.tests;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Proxy object to invoke the test operations
 * @author Miguel Angel Garcia
 */
public class TestsProxy extends BaseServiceProxy {  
                         
    public TestsProxy(AuthData authData){
        super(authData);
    }
    
    /**
     * Says hello (for testing purposes)
     * @param String name
     * @return SayHelloResponseData
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public SayHelloResponseData sayHello(String name) throws ServiceDefinitionException, ServiceInvocationException  {
        SayHelloResponseData sayHelloRes = null;
      
        Object[] params = {name};
        ICallableResponse res = invoke(getCallerMethodName(), params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            LinkedTreeMap data = (LinkedTreeMap) jRes.getResponseData();
            
            String greeting = null;
            
            if (data.containsKey("greeting")){
                greeting = data.get("greeting").toString();
            }
            
            sayHelloRes = new SayHelloResponseData();
            sayHelloRes.setGreeting(greeting);
        }
        
        return sayHelloRes;
    }
    
}
