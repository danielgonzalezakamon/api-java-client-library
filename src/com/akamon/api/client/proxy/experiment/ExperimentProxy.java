package com.akamon.api.client.proxy.experiment;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 03/12/13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 * @author Jano Hernandez - Victor Bolinches
 */
public class ExperimentProxy extends BaseServiceProxy
{
	/**
	 * Constructor
	 * @param paramAuthData
         * @param logger
	 */
	public ExperimentProxy(AuthData paramAuthData, java.util.logging.Logger logger)
	{
            super(paramAuthData, logger);
	}
        
        public ExperimentProxy(AuthData paramAuthData)
	{
            super(paramAuthData, null);
	}

	/**
	 *
	 * @param slug
	 * @param public_user_Id
	 * @return
	 * @throws ServiceDefinitionException
	 * @throws ServiceInvocationException
	 */
	public ExperimentResponseData getExperiment(String slug, String public_user_Id) throws ServiceDefinitionException, ServiceInvocationException
	{
		ExperimentResponseData localExperimentResponseData = null;
		Object[] params = { slug, public_user_Id };

		ICallableResponse localICallableResponse = invoke("getExperiment", params);
		if (localICallableResponse instanceof JsonCallableResponse)
		{
			JsonCallableResponse localJsonCallableResponse = (JsonCallableResponse)localICallableResponse;

			try {
				localExperimentResponseData = localJsonCallableResponse.buildResponseDataObject(ExperimentResponseData.class);
			}
			catch (Exception localException){
				throw new ServiceInvocationException("getExperiment", "Response parse error", localException);
			}
		}

		return localExperimentResponseData;
	}

}
