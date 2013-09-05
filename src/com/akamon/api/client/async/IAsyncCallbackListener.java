package com.akamon.api.client.async;


/**
 * LIstener contract for modeling the response to a "finished async api operation call" event
 * @author Miguel Angel Garcia
 */
public interface IAsyncCallbackListener {
 
    /**
     * Success invocation response action
     * @param serviceCode Operation service code
     * @param response Response obtained from the service invocation
     * @param requestParams Params passed in the service invocation
     * @param extraData Extra data suinistred by the caller
     */
    public void successAction(String serviceCode, Object response, Object[] requestParams, Object extraData);    
    
    /**
     * Error invocation response action (the operation invocation failed)
     * @param serviceCode Operation service code
     * @param ex Exception raised
     * @param requestParams Params passed in the service invocation
     * @param extraData Extra data suinistred by the caller
     */
    public void errorAction(String serviceCode, Exception ex, Object[] requestParams, Object extraData);    
    
}
