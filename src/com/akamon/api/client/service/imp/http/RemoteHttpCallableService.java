package com.akamon.api.client.service.imp.http;

import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.IRemoteHttpCallableService;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.ServiceConfigManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;

/**
 *
 * @author Miguel Angel Garcia
 */
public class RemoteHttpCallableService implements IRemoteHttpCallableService {

    private String serviceCode;
    
    private AuthData authData;
    
    private String httpMethod;
    
    private String url;
    
    private ServiceConfigManager manager;
    
    public static void main(String args[]){
        AuthData auth = new AuthData("a", "b", "c");
        try {
            ServiceConfigManager.registerConfigDir("C:\\Users\\Miguel Angel Garcia\\Documents\\NetBeansProjects\\api-java-client-library\\service_definitions");
            RemoteHttpCallableService s = new RemoteHttpCallableService("sayHello", auth, "GET");
        } catch (ServiceDefinitionException ex) {
            //Logger.getLogger(RemoteHttpCallableService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }    
    
    public RemoteHttpCallableService(String serviceCode, AuthData authData, String httpMethod) throws ServiceDefinitionException{
        setServiceCode(serviceCode);
        setAuthData(authData);
        setHttpMethod(httpMethod);
        
        manager = ServiceConfigManager.newInstance(serviceCode);
        Object url = manager.getConfigParameter("url");
        
        if (url == null){
            throw new ServiceDefinitionException(serviceCode);
        }
        else {
            setUrl(url.toString());
        }        
    }
    
    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getHttpMethod() {
        return this.httpMethod;
    }

    @Override
    public String getAppCode() {
        return this.authData.getAppCode();
    }

    @Override
    public String getAppToken() {
        return this.authData.getAppToken();
    }

    @Override
    public String getServideCode() {
        return this.serviceCode;
    }

    @Override
    public String getFormat() {
        return this.authData.getFormat();
    }

    @Override
    public boolean validateServiceParameters(NameValuePair[] parameters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ICallableResponse invoke(NameValuePair[] parameters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param serviceCode the serviceCode to set
     */
    private void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * @param authData the authData to set
     */
    private void setAuthData(AuthData authData) {
        this.authData = authData;
    }

    /**
     * @param httpMethod the httpMethod to set
     */
    private void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * @param url the url to set
     */
    private void setUrl(String url) {
        this.url = url;
    }
    
    
}
