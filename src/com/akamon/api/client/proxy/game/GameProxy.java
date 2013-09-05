package com.akamon.api.client.proxy.game;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Proxy object to invoke the game operations
 * @author Miguel Angel Garcia
 */
public class GameProxy extends BaseServiceProxy { 
        
    public GameProxy(AuthData authData){
        super(authData);
    }
    
    /**
     * Creates a game match
     * @param game_id Game identifier
     * @param users public users ids array
     * @return Created Match id
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */

    public Integer createMatch(Integer game_id, Object[] users) throws ServiceDefinitionException, ServiceInvocationException{
        Integer matchId = null;
        
        Object[] params = {game_id, users};
        
        ICallableResponse res = invoke("createMatch", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            matchId = jRes.getResponseIntegerValue("match_id");
        }
        
        return matchId;
    }
    
    /**
     * Updates the match status
     * @param game_id Game identifier
     * @param match_id Match identifier
     * @param state Match state
     * @return The match final status
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public String updateMatch(Integer game_id, Integer match_id, String state) throws ServiceDefinitionException, ServiceInvocationException{
        String resStatus = null;
        Object[] params = {game_id, match_id, state};
        
        ICallableResponse res = invoke("updateMatch", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            resStatus = jRes.getResponseIntegerValue("match_status").toString();            
        }
        
        return resStatus;
    }
    
    /**
     * Performes a chips operation by a user in a game context
     * @param gameId Game identifier
     * @param matchId Match identifier
     * @param publicUserId User identifier
     * @param chips Number of chips (positive or negative) to upate the user balance
     * @return MatchChipsResponseData
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public MatchChipsResponseData matchChips(Integer gameId, Integer matchId, String publicUserId, Integer chips) throws ServiceDefinitionException, ServiceInvocationException{
        MatchChipsResponseData responseData = new MatchChipsResponseData();
        Object[] params = {gameId, matchId, publicUserId, chips};
        
        ICallableResponse res = invoke("matchChips", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            LinkedTreeMap data = (LinkedTreeMap) jRes.getResponseData();
            

            String resPublicUserId = null;
            Integer resChips = null;
            
            if (data.containsKey("public_user_id")){
                resPublicUserId = data.get("public_user_id").toString();
            }
            
            if (data.containsKey("chips")){
                resChips = Integer.parseInt(data.get("chips").toString());
            }
            
            responseData.setPublicUserId(resPublicUserId);
            responseData.setChips(resChips);

        }
        
        return responseData;
    }        
}
