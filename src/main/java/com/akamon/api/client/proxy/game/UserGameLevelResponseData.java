package com.akamon.api.client.proxy.game;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 25/02/14
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 *
 * Class UserGameLevelResponseData
 * @package Akamon\API\Client\Proxy\Game
 */
public class UserGameLevelResponseData
{
	/**
	 * Level at which the user are
	 *
	 * @var Integer
	 */
	private Integer level;

	/**
	 * Points the user has at level
	 *
	 * @var Integer
	 */
	private Integer points;

	/**
	 * Loops that the user has do to the level
	 *
	 * @var Integer
	 */
	private Integer loops;

	/**
	 * @param level
	 * @param points
	 * @param loops
	 */
	public UserGameLevelResponseData(Integer level, Integer points, Integer loops)
	{
		this.level  = level;
		this.points = points;
		this.loops  = loops;
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
	public Integer getLoops()
	{
		return this.loops;
	}

	/**
	 * @return Integer
	 */
	public Integer getPoints()
	{
		return this.points;
	}
}