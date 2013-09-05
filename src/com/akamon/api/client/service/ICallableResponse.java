package com.akamon.api.client.service;

/**
 * Reponse from a service call
 * @author Miguel Angel Garcia
 */
public interface ICallableResponse {
 
    /**
     * Gets the error code (0 it all was o.k.)
     * @return Http response code
     */
    public int getErrorCode();
    
    /**
     * Gets the error description (empty it all was o.k.)     
     * @return Error description
     */
    public String getErrorString();
    
    /**
     * Sets the response received from the service code
     * @param data Response obtained from server
     */
    public void setResponseData(Object data);
    
    /**
     * Gets the response received from the service execution
     * @return Response obtained from server
     */
    public Object getResponseData();    
}
