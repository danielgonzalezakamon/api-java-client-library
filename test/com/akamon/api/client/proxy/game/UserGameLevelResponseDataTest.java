package com.akamon.api.client.proxy.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 25/02/14
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class UserGameLevelResponseDataTest
{
	private UserGameLevelResponseData userGameLevelResponseData;

	private final int level = 200;
	private final int point = 1000000;
	private final int loop = 2;


	@Before
	public void setUp() throws Exception
	{
		userGameLevelResponseData = new UserGameLevelResponseData(level,point,loop);

	}

	@Test
	public void testGetLevel() throws Exception
	{
		final int expected = level;
		final int result = userGameLevelResponseData.getLevel();

		assertEquals("not same level",result,expected);
	}

	@Test
	public void testGetLoops() throws Exception
	{
		final int expected = loop;
		final int result = userGameLevelResponseData.getLoops();

		assertEquals("not same loop",result,expected);
	}

	@Test
	public void testGetPoints() throws Exception
	{
		final int expected = point;
		final int result = userGameLevelResponseData.getPoints();

		assertEquals("not same points",result,expected);
	}
}
