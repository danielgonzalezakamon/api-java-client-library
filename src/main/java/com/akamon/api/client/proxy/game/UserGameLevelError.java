package com.akamon.api.client.proxy.game;

public class UserGameLevelError 
{
	/**
	* @var int
	*/
	private Integer code;
	
	/**
	* @var string
	*/
	private String message;
	
	/**
	* @param int $errorCode
	* @param string $message
	*/
	public UserGameLevelError(Integer errorCode, String message)
	{
	    this.code = errorCode;
	    this.message = message;
	}
	
	/**
	* @return int
	*/
	public Integer getErrorCode()
	{
	    return this.code;
	}
	
	/**
	* @return string
	*/
	public String getMessage()
	{
	    return this.message;
	}
}
