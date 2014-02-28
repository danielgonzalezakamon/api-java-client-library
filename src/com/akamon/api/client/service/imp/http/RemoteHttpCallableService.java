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
import com.akamon.api.client.error.ServiceInvocationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Callable service (http based services)
 * @author Miguel Angel Garcia
 */
public class RemoteHttpCallableService implements IRemoteHttpCallableService {
    
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    private String serviceCode;
    
    private AuthData authData;
    
    private String httpMethod;
    
    private String url;
    
    private ServiceConfigManager manager;
    
    java.util.logging.Logger logger;
    
    private final static Pattern paramUrlPattern = Pattern.compile("\\{[a-zA-Z0-9_\\-]+\\}");        
    
    
    /**
     * Builds the object
     * @param serviceCode Operation service code
     * @param authData Authentication data object
     * @throws ServiceDefinitionException 
     */
    public RemoteHttpCallableService(String serviceCode, AuthData authData, java.util.logging.Logger logger) throws ServiceDefinitionException {
        this.logger = logger;
        
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
    
    public RemoteHttpCallableService(String serviceCode, AuthData authData) throws ServiceDefinitionException {
        this(serviceCode, authData, null);
    }
    
    /**
     * Gets the service url
     * @return Service url
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * Gets the service http method
     * @return Http method (GET, POST, PUT, DELETE, ..)
     */
    @Override
    public String getHttpMethod() {
        return this.httpMethod;
    }

    /**
     * Gets the public client id
     * @return Public client identifier
     */
    @Override
    public String getAppCode() {
        return this.authData.getAppCode();
    }

    /**
     * Gets the private client data used to perform the signature
     * @return Private client token
     */
    @Override
    public String getAppToken() {
        return this.authData.getAppToken();
    }

    /**
     * Gets the operation service code
     * @return Service to call identifier
     */
    @Override
    public String getServiceCode() {
        return this.serviceCode;
    }

    /**
     * Gest the signature algorithm
     * @return Signature algorithm
     */
    @Override
    public String getFormat() {
        return this.authData.getFormat();
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
    
    private void log(String message, Level level){
        if ( this.logger != null ){
            Date now = new Date();
            StringBuilder messageBuilder = new StringBuilder();            
            
            messageBuilder.append(dateFormat.format(now));
            messageBuilder.append(" ");
            messageBuilder.append(message);
            
            this.logger.log(level, messageBuilder.toString());
        } 
   }
   
   private void log(String message){
        log(message, Level.FINE);
   }   
    
    /**
     * Validates the request parameters
     * @param invokationData Data to send with the request
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    private void validateServiceParameters(Object[] invokationData) throws ServiceDefinitionException, ServiceInvocationException{
        ServiceValidator validator = new ServiceValidator();
        validator.validate(this.getServiceCode(), invokationData);
    }
    
    /**
     * Performs the http invokation
     * @param invokationData Data to send with the request
     * @return Response obtained from server
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    @Override
    public ICallableResponse invoke(Object[] invokationData) throws ServiceDefinitionException, ServiceInvocationException{
        ICallableResponse callableResponse = null;
        
        synchronized(RemoteHttpCallableService.class){
        
        try {                      
            NameValuePair[] httpParams = buildHttpInvokationParameters(invokationData);                         
            String serviceUrl = replaceUrlWithRouteParams(getUrl(), httpParams);                                   
            
            HttpClient client = new HttpClient(getAppCode(), getAppToken(), this.logger);
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
            log("API-client-library (invoke " + this.getServiceCode() + ") ServiceDefinitionException: " + sde.getMessage(), Level.SEVERE);
            throw sde;
        }
        catch (ServiceInvocationException sie){
            log("API-client-library (invoke " + this.getServiceCode() + ") ServiceInvocationException: " + sie.getMessage(), Level.SEVERE);
            throw sie;
        }
        catch (Exception e){
            log("API-client-library (invoke " + this.getServiceCode() + ") " + e.getClass().getName() + ": " + e.getMessage(), Level.SEVERE);
            throw new ServiceInvocationException(this.getServiceCode(), e);
        }           
        }
        
        return callableResponse;    
    }
    
    /**
     * Builds the parameters data structure needed to make the remote call
     * @param invokationData Data to send with the request
     * @return Http parameters as a NameValuePair, to use with the apache HttpClient library 
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
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
    
    /**
     * Converts the parameter data from invocation format to hashmap format
     * @param httpParams
     * @return Parameters as a key-values HashMap object
     */
    private HashMap<String,Object> invocationParamArrayToHashmap(NameValuePair[] httpParams){
        LinkedHashMap<String,Object> map = new LinkedHashMap();
        
        for (NameValuePair param : httpParams){
            map.put(param.getName(), param.getValue());
        }
        
        return map;
    }
    
    /**
     * Replaces all the parameter marked in the url with the right values. The parameters
     * must be marked in the url with the sintax: {parameter_name). 
     * For example: http://api.akamon.com/../{param_1}/.../{param_2} 
     * @param url Invocation url
     * @param httpParams Params to performe the substitution
     * @return Final valid url
     */
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
