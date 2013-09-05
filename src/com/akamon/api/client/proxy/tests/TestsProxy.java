package com.akamon.api.client.proxy.tests;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;

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
     * @param name
     * @return SayHelloResponseData
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public SayHelloResponseData sayHello(String name) throws ServiceDefinitionException, ServiceInvocationException  {
        SayHelloResponseData sayHelloRes = null;
      
        Object[] params = {name};
        ICallableResponse res = invoke("sayHello", params);

        if (res instanceof JsonCallableResponse){            
            
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            try {
                sayHelloRes = jRes.buildResponseDataObject(SayHelloResponseData.class);                
            } 
            catch (Exception ex) {
                throw new ServiceInvocationException("sayHello", "Response parse error", ex);
            }             
        }
        
        return sayHelloRes;
    }
    
}
