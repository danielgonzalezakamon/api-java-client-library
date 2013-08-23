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
    public Integer createMatch(Integer game_id, String[] users) throws ServiceDefinitionException, ServiceInvocationException{
        Integer matchId = null;
        
        Object[] params = {game_id, users};
        ICallableResponse res = invoke(getCallerMethodName(), params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            matchId = jRes.getResponseIntegerValue("match_id");
        }
        
        return matchId;
    }
    
    /**
     * Updates the match status
     * @param Integer game_id
     * @param Integer match_id
     * @param String state
     * @return Integer (The match final status code)
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public Integer updateMatch(Integer game_id, Integer match_id, String state) throws ServiceDefinitionException, ServiceInvocationException{
        Integer status = null;
        
        Object[] params = {game_id, match_id, state};
        ICallableResponse res = invoke(getCallerMethodName(), params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            status = jRes.getResponseIntegerValue("match_status");            
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
    public MatchChipsResponseData matchChips(Integer gameId, Integer matchId, String publicUserId, Integer chips) throws ServiceDefinitionException, ServiceInvocationException{
        MatchChipsResponseData responseData = new MatchChipsResponseData();
        
        Object[] params = {gameId, matchId, publicUserId, chips};
        ICallableResponse res = invoke(getCallerMethodName(), params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            LinkedTreeMap data = (LinkedTreeMap) jRes.getResponseData();
            
            String responsePublicUserId = null;
            Integer responseChips = null;
            
            if (data.containsKey("public_user_id")){
                responsePublicUserId = data.get("public_user_id").toString();
            }
            
            responseChips = jRes.getResponseIntegerValue("chips");
                        
            responseData.setPublicUserId(responsePublicUserId);
            responseData.setChips(responseChips);
        }
        
        return responseData;
    }        
}
