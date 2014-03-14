package com.akamon.api.client.proxy.game;

import java.util.Map;
import java.util.Set;

/**
* Class CollectionUserGameLevelResponseData
* @package Akamon\API\Client\Proxy\Game
*/
public class CollectionUserGameLevelResponseData
{
	/**
	* @var array
	*/
	private Map<String, UserGameLevelResponseData> success;

	/**
	* @var array
	*/
	private Map<String, UserGameLevelError> errors;


	/**
	* @return array
	*/
	public Set<String> getSuccessfulUsersPublicIds()
	{
		return success.keySet();
	}
	
	/**
	* @return array
	*/
	public Set<String> getErrorUsersPublicIds()
	{
		 return errors.keySet();
	}
	

	/**
	* @param $publicUserId
	*
	* @return null|UserGameLevelResponseData
	*/
	public UserGameLevelResponseData getUserGameLevelForUser(String publicUserId)
	{
		UserGameLevelResponseData userGameLevel = null;
	
		if (success.containsKey(publicUserId)) 
		{
			userGameLevel = success.get(publicUserId);
		}
	
		return userGameLevel;
	}
	
	/**
	* @param $publicUserId
	*
	* @return null|UserGameLevelError
	*/
	public UserGameLevelError getErrorForUser(String publicUserId)
	{
		UserGameLevelError error = null;
		
		if (errors.containsKey(publicUserId)) 
		{
			error = errors.get(publicUserId);
		}
		
		return error;
	}
}

