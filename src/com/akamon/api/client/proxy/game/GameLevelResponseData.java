package com.akamon.api.client.proxy.game;

/**
* Class GameLevelResponseData
* @package Akamon\API\Client\Proxy\Game
*/
public class GameLevelResponseData {

    /**
	* @var int
	*/
	private Integer level;

	/**
	* @var int
	*/
    private Integer points;

	/**
	* @var int
	*/
    private Integer limbo;

	    /**
	* @param int $level
	* @param int $points
	* @param int $limbo
	*/
	public GameLevelResponseData(Integer level, Integer points, Integer limbo)
	{
	    this.level = level;
	    this.points = points;
	    this.limbo = limbo;
	}

	/**
	 * @return Integer
	 */
	public Integer getLevel()
	{
		return this.level;
	}

	/**
	 * @return Integer
	 */
	public Integer getPoints()
	{
		return this.points;
	}

	/**
	* @return bool
	*/
    public boolean isLimbo()
    {
        return this.limbo == 1;
    }
	
}
