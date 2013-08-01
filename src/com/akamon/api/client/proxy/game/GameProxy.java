package com.akamon.api.client.proxy.game;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.ServiceConfigManager;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;
import com.google.gson.internal.LinkedTreeMap;

/**
 *
 * @author Miguel Angel Garcia
 */
public class GameProxy extends BaseServiceProxy {       
    
    public GameProxy(AuthData authData){
        super(authData);
    }
    
    /**
     * Creates a game match
     * @param Integer game_id
     * @param Object[] users (public users id's array)
     * @return Integer (Match id)
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public Integer createMatch(Object... params) throws ServiceDefinitionException, ServiceInvocationException{
        Integer matchId = null;
        
        ICallableResponse res = invoke(getCallerMethodName(), params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            LinkedTreeMap data = (LinkedTreeMap) jRes.getResponseData();
            
            if (data.containsKey("match_id")){
                matchId = Integer.parseInt(data.get("match_id").toString());
            }
        }
        
        return matchId;
    }
    
    /**
     * Updates the match status
     * @param Integer game_id
     * @param Integer match_id
     * @param String state
     * @return String (The match final status)
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public String updateMatch(Object... params) throws ServiceDefinitionException, ServiceInvocationException{
        String status = null;
        
        ICallableResponse res = invoke(getCallerMethodName(), params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            LinkedTreeMap data = (LinkedTreeMap) jRes.getResponseData();
            
            if (data.containsKey("match_status")){
                status = data.get("match_status").toString();
            }
        }
        
        return status;
    }
    
    /**
     * Performes a chips operation by a user in a game context
     * @param Integer gameId Game identifier
     * @param Integer matchId Match identifier
     * @param String publicUserId User identifier
     * @param Integer chips Number of chips (positive or negative) to upate the user balance
     * @return MatchChipsResponseData
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public MatchChipsResponseData matchChips(Object... params) throws ServiceDefinitionException, ServiceInvocationException{
        MatchChipsResponseData responseData = new MatchChipsResponseData();
        
        ICallableResponse res = invoke(getCallerMethodName(), params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            LinkedTreeMap data = (LinkedTreeMap) jRes.getResponseData();
            
            String publicUserId = null;
            Integer chips = null;
            
            if (data.containsKey("public_user_id")){
                publicUserId = data.get("public_user_id").toString();
            }
            
            if (data.containsKey("chips")){
                chips = Integer.parseInt(data.get("chips").toString());
            }
            
            responseData.setPublicUserId(publicUserId);
            responseData.setChips(chips);
        }
        
        return responseData;
    }
}
