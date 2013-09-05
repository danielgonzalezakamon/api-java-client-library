package com.akamon.api.client.proxy.game;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;

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
    public CreateMatchResponseData createMatch(Integer game_id, Object[] users) throws ServiceDefinitionException, ServiceInvocationException{       
        CreateMatchResponseData response = null;
        Object[] params = {game_id, users};
        
        ICallableResponse res = invoke("createMatch", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            try {
                response = jRes.buildResponseDataObject(CreateMatchResponseData.class);            
            }
            catch (Exception ex){
                 throw new ServiceInvocationException("createMatch", "Response parse error", ex);
            }
        }
        
        return response;
    }
    
    /**
     * Updates the match status
     * @param game_id Game identifier
     * @param match_id Match identifier
     * @param state Match state (initiated, finished)
     * @return The match final status
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public UpdateMatchResponseData updateMatch(Integer game_id, Integer match_id, String state) throws ServiceDefinitionException, ServiceInvocationException{
        
        UpdateMatchResponseData response = null;        
        Object[] params = {game_id, match_id, state};
        
        ICallableResponse res = invoke("updateMatch", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            response = jRes.buildResponseDataObject(UpdateMatchResponseData.class);                        
        }
        
        return response;
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
        MatchChipsResponseData response = null;
        Object[] params = {gameId, matchId, publicUserId, chips};
        
        ICallableResponse res = invoke("matchChips", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            response = jRes.buildResponseDataObject(MatchChipsResponseData.class);            
        }
        
        return response;
    }        
}
