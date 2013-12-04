package com.akamon.api.client.proxy.experiment;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 03/12/13
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 * @author Victor Bolinches Marin
 * Class that provide the Test A/B
 */
public class ExperimentResponseData
{
	/** Represent de test A/B */
	private String experiment;

	/**
	 * Method that get the experiment
	 * @return
	 */
	public String getExperiment()
	{
		return this.experiment;
	}

	/**
	 * Method that set the experiment
	 * @param paramString
	 */
	public void setExperiment(String paramString)
	{
		this.experiment = paramString;
	}
}
