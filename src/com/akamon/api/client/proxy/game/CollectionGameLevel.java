package com.akamon.api.client.proxy.game;

import java.util.Arrays;
import java.util.List;

/**
* Class CollectionGameLevel
* @package Akamon\API\Client\Proxy\Game
*/
public class CollectionGameLevel {

	/**
	* @var array
	*/
	private List<GameLevelResponseData> gameLevels;
	
	
	/**
	* @return GameLevelResponseData[]
	*/
	public List<GameLevelResponseData> getGameLevels()
	{
	    return this.gameLevels;
	}
	
}
