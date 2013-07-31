package com.akamon.api.client.service.imp.http;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.net.HttpClient;
import com.akamon.api.client.net.HttpResponseData;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.IRemoteHttpCallableService;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.ServiceConfigManager;
import com.akamon.api.client.service.error.BadHttpResponseInvocationException;
import com.akamon.api.client.service.validation.ServiceValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
    
    private final static Pattern paramUrlPattern = Pattern.compile("\\{[a-zA-Z0-9_\\-]+\\}");             
    
    public RemoteHttpCallableService(String serviceCode, AuthData authData) throws ServiceDefinitionException{
        setServiceCode(serviceCode);
        setAuthData(authData);
        setHttpMethod(httpMethod);
        
        manager = ServiceConfigManager.newInstance(serviceCode);
        Object serviceUrl = manager.getConfigParameter("url");
        Object serviceMethod = manager.getConfigParameter("method");
        
        if ( (serviceUrl == null) || (serviceMethod == null) ) {
            throw new ServiceDefinitionException(serviceCode);
        }
        else {
            setUrl(serviceUrl.toString());
            setHttpMethod(serviceMethod.toString());
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
    public String getServiceCode() {
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
    
    private void validateServiceParameters(Object[] invokationData) throws ServiceDefinitionException, ServiceInvocationException{
        ServiceValidator validator = new ServiceValidator();
        validator.validate(this.getServiceCode(), invokationData);
    }
        
    
    public ICallableResponse invoke(Object[] invokationData) throws ServiceDefinitionException, ServiceInvocationException{
        ICallableResponse callableResponse = null;
        
        try {                      
            NameValuePair[] httpParams = buildHttpInvokationParameters(invokationData);                         
            String serviceUrl = replaceUrlWithRouteParams(getUrl(), httpParams);                                   
            
            HttpClient client = new HttpClient(getAppCode(), getAppToken());
            HttpResponseData response = client.execute(serviceUrl, getHttpMethod(), httpParams);            
            int responseCode = response.getResponseCode();
            String responseString = response.getResponseString();
            
            if ( (responseCode < 200) || (responseCode > 299) ){
                throw new BadHttpResponseInvocationException(getServiceCode(), responseString,responseCode);                
            }
            else {
                callableResponse = new JsonCallableResponse(getServiceCode(), responseString);
            }
            
        }
        catch (ServiceDefinitionException sde){
            throw sde;
        }
        catch (ServiceInvocationException sie){
            throw sie;
        }
        catch (Exception e){
            throw new ServiceInvocationException(this.getServiceCode(), e);
        }                                     
        
        return callableResponse;    
    }
    
    private NameValuePair[] buildHttpInvokationParameters(Object[] invokationData) throws ServiceDefinitionException, ServiceInvocationException{
         NameValuePair[] httpParams = null;
        
        try {
            int invokationDataLength = (invokationData == null) ? 0 : invokationData.length;
            SerializerHelper serializer = new SerializerHelper();
            
            validateServiceParameters(invokationData);
        
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            ServiceConfigManager configManager = ServiceConfigManager.newInstance(getServiceCode());
        
            HashMap<String,Object> configParams = (HashMap<String,Object>) configManager.getConfigParameter("params");
            int dataIndex = -1;
            
            for (String paramName : configParams.keySet()){
                String paramValue = null;
                
                if (++dataIndex < invokationDataLength){
                    paramValue = serializer.serialize(invokationData[dataIndex]);
                }                                
                
                System.out.println(paramName + " = " + (paramValue == null ? null : paramValue));
                
                params.add(new BasicNameValuePair(paramName, paramValue == null ? null : paramValue));                
            }  
                        
            httpParams = params.toArray(new NameValuePair[0]);
        }
        catch (ServiceDefinitionException sde){
            throw sde;
        }
        catch (ServiceInvocationException sie){
            throw sie;
        }
        catch (Exception e){
            throw new ServiceInvocationException(this.getServiceCode(), e);
        }
        
        return httpParams;
    }
    
    private HashMap<String,Object> invocationParamArrayToHashmap(NameValuePair[] httpParams){
        LinkedHashMap<String,Object> map = new LinkedHashMap();
        
        for (NameValuePair param : httpParams){
            map.put(param.getName(), param.getValue());
        }
        
        return map;
    }
    
    public String replaceUrlWithRouteParams(String url, NameValuePair[] httpParams){        
        Matcher matcher = paramUrlPattern.matcher(url);
        
        LinkedHashMap<String,String> replaces = new LinkedHashMap();
        HashMap<String,Object> paramsMap = null;
        
        while (matcher.find()){                        
            String group = matcher.group();
            String paramToReplace = group.substring(1, group.length() - 1);
            
            if (paramsMap == null){
                paramsMap = invocationParamArrayToHashmap(httpParams);
            }
            
            if (paramsMap.containsKey(paramToReplace)){
                replaces.put(paramToReplace,paramsMap.get(paramToReplace).toString());
            }
        }
        
        for (String replace : replaces.keySet()){
            url = url.replaceAll("\\{" + replace + "\\}", replaces.get(replace));
        }
        
        return url;
    }
    
    
}
