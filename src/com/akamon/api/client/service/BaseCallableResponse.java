package com.akamon.api.client.service;

/**
 * Base class to encapsulate the response obtained from server
 * @author Miguel Angel Garcia
 */
public abstract class BaseCallableResponse implements ICallableResponse {

    protected String serviceCode;
    protected Object rawData = null;
    protected Object responseData = null;
    
    /**
     * Builds the response
     * @param serviceCode Operation service code
     * @param rawData Raw data obtained from server
     */
    public BaseCallableResponse(String serviceCode, Object rawData){
        this.serviceCode = serviceCode;
        setRawData(rawData);
    }
    
    /**
     * Gets the raw data obtained from server
     * @return raw data obtained from server
     */
    public Object getRawData(){
        return rawData;
    }
    
    /**
     * Sets the raw data obtained from server
     * @param raw data obtained from server
     */
    protected void setRawData(Object rawData){
        this.rawData = rawData;
    }
    
    /**
     * Gets the operation service code
     * @return Operation service code
     */
    public String getServiceCode(){
        return serviceCode;
    }

    /**
     * Sets the response data (in a processed format)
     * @param responseData response data (in a processed format)
     */
    @Override
    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    /**
     * Gets the response data (in a processed format)
     * @return responseData response data (in a processed format)
     */
    @Override
    public Object getResponseData() {
        return responseData;
    }
    
}
