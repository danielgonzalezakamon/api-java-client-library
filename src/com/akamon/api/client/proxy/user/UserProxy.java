package com.akamon.api.client.proxy.user;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.proxy.user.UserResponseData;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;
import java.util.HashMap;

/**
 * Proxy object to invoke the user operations 
 * @author Miguel Angel Garcia
 */
public class UserProxy extends BaseServiceProxy { 
                    
    public UserProxy(AuthData authData, java.util.logging.Logger logger){
        super(authData, logger);
    }
    
    public UserProxy(AuthData authData){
        this(authData, null);
    }
    
    /**
     * Creates a user session (to pass the user info safely).
     * This session must be consumed using the userSessionGetContent() operation
     * (normally this is created by the web server, and passed to the client
     * instead of 
     * 
     * @param userName 
     */
    public UserSessionResponseData createUserSession (String userName) throws ServiceDefinitionException, ServiceInvocationException {
        UserSessionResponseData response = null;
        Object[] params = {userName};

        ICallableResponse res = invoke("createUserSession", params);

        if (res instanceof JsonCallableResponse) {
            JsonCallableResponse jRes = (JsonCallableResponse) res;

            try {
                response = jRes.buildResponseDataObject(UserSessionResponseData.class);
            } catch (Exception ex) {
                throw new ServiceInvocationException("createUserSession", "Response parse error", ex);
            }
        }

        return response;
    }          

    /**
     * @author Victor Bolinches Marin Method that though the sessionID of portal
     * obtains the user properties
     * @param sessionID (Required)
     * @throws ServiceInvocationException
     * @return User created data
     */
    public UserResponseData userSessionGetContent(String sessionID) throws ServiceInvocationException, ServiceDefinitionException {
        UserResponseData response = null;
        Object[] params = {sessionID};

        ICallableResponse res = invoke("userSessionGetContent", params);

        if (res instanceof JsonCallableResponse) {
            JsonCallableResponse jRes = (JsonCallableResponse) res;

            try {
                response = jRes.buildResponseDataObject(UserResponseData.class);
            } catch (Exception ex) {
                throw new ServiceInvocationException("userSessionGetContent", "Response parse error", ex);
            }
        }

        return response;
    }
    
    /**
     * Create a new user accouunt
     * @param name (Required)  User name
     * @param password (Required)  User password
     * @param email (Required) email
     * @param genre (Required)  User genre. V for man, H for woman and I when it's unknown
     * @param partnerAlias (Required) Partner alias (www for www.mundijuegos.com)
     * @param ip User ip
     * @param country Two letter iso country code
     * @param friend Username of the friend that suggested this registration
     * @param additionalParams json-code-key-valued-array with special params, for example campaign params (utm_medium, utm_term, utm_content, utm_campaign, utm_source, ..)
     * @return User created data
     */
    public UserResponseData registerWebUser(String name, 
            String password, 
            String email,
            String genre,
            String partnerAlias,
            String ip,
            String country,
            String friend,
            HashMap<String,String> additionalParams) throws ServiceDefinitionException, ServiceInvocationException
    {
        UserResponseData response = null;
        Object[] params = {name, 
            password, 
            email, 
            genre, 
            partnerAlias, 
            ip,
            country,
            friend,
            additionalParams};
        ICallableResponse res = invoke("registerWebUser", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            try {
                response = jRes.buildResponseDataObject(UserResponseData.class);            
            }
            catch (Exception ex){
                 throw new ServiceInvocationException("registerWebUser", "Response parse error", ex);
            }
        }
        
        return response;
    }
    
    /**
     * Create a new user accouunt
     * @param name (Required) User name
     * @param password (Required) User password
     * @param email (Required) email
     * @param genre (Required) User genre. V for man, H for woman and I when it's unknown
     * @param partnerAlias (Required) Partner alias (www for www.mundijuegos.com)    
     * @return User created data
     */
    public UserResponseData registerWebUser(String name, 
            String password, 
            String email,
            String genre,
            String partnerAlias) throws ServiceDefinitionException, ServiceInvocationException
    {
        return registerWebUser(name, password, email, genre, partnerAlias, null, null, null, null);
    }
    
    /**
     * Links a user from an external site (for example, facebook). If the user has not been linked
     * before, a new account at our side it's created. If not, the before created user reference
     * is returned
     *      
     * @param providerName (Required)Provider name. It must be facebook or taringa
     * @param providerUserId (Required) User id in the external system (in the case of facebook, user id)
     * @param email (Required) User email
     * @param genre (Required) User genre (V for man, H for woman, I for not defined yet)
     * @param ip (Required) User ip (number dotted string format)
     * @param name User desired name (5-15 letters and numbers string)
     * @param password User desired password
     * @param country two-letter iso country code
     * @param partnerAlias Alias of the partner (www by default)
     * @param friend User name from the friend that enrolled this user
     * @param additionalParams key-valued structure with special params :
     *  channel => ‘Facebook’, ‘Portal’, ‘IOS’, ‘Android’
     *  game_id => Game id responsible for the user register
     *  bu_lang => user country in the ISO 3166 two letter country code
     *  utm_medium => utm campaign parameter
     *  utm_term => utm campaign parameter
     *  utm_source => utm campaign parameter 
     *  utm_content => utm campaign parameter
     * @param realName User real name (for example: 'John Smith'). It helps to generate the user loginname, when is not possible to do it from his email
     * @return User data
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public UserResponseData registerOrLoginExternalUser(String providerName, 
            String providerUserId, 
            String email, 
            String genre, 
            String ip, 
            String name, 
            String password, 
            String country, 
            String partnerAlias, 
            String friend, 
            HashMap<String,String> additionalParams, 
            String realName) throws ServiceDefinitionException, ServiceInvocationException
    {
        UserResponseData response = null;
        
        Object[] params = {providerName,
            providerUserId,
            name,email,
            password,
            genre,
            ip,
            country,
            partnerAlias,
            friend,
            additionalParams,
            realName};
        
        ICallableResponse res = invoke("registerOrLoginExternalUser", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            try {
                response = jRes.buildResponseDataObject(UserResponseData.class);            
            }
            catch (Exception ex){
                 throw new ServiceInvocationException("registerWebUser", "Response parse error", ex);
            }
        }
        
        return response;                
    }
    
    /**
     * Links a user from an external site (for example, facebook). If the user has not been linked
     * before, a new account at our side it's created. If not, the before created user reference
     * is returned
     *      
     * @param providerName (Required)Provider name. It must be facebook or taringa
     * @param providerUserId (Required) User id in the external system (in the case of facebook, user id)
     * @param email (Required) User email
     * @param genre (Required) User genre (V for man, H for woman, I for not defined yet)
     * @param ip (Required) User ip (number dotted string format)
     * @return User data
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public UserResponseData registerOrLoginExternalUser(String providerName, 
            String providerUserId, 
            String email, 
            String genre, 
            String ip) throws ServiceDefinitionException, ServiceInvocationException
    {
        return registerOrLoginExternalUser(providerName, 
            providerUserId, 
            email, 
            genre, 
            ip, 
            null, 
            null, 
            null, 
            null, 
            null, 
            null, 
            null);
    }
    
    /**
     * Authenticates an user by its username and password
     * @param userName (Required) User name
     * @param password (Required) User password
     * @return User data
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public UserResponseData authenticateUser(String userName, String password) throws ServiceDefinitionException, ServiceInvocationException {
        UserResponseData response = null;
        
        Object[] params = {userName, password};
        
        ICallableResponse res = invoke("authenticateUser", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            try {
                response = jRes.buildResponseDataObject(UserResponseData.class);            
            }
            catch (Exception ex){
                 throw new ServiceInvocationException("authenticateUser", "Response parse error", ex);
            }
        }
        
        return response;   
    }   
    
}
