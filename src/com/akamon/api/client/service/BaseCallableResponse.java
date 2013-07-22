package com.akamon.api.client.service;

/**
 *
 * @author Miguel Angel Garcia
 */
public abstract class BaseCallableResponse implements ICallableResponse {

    protected String serviceCode;
    protected Object rawData = null;
    protected Object responseData = null;
    
    public BaseCallableResponse(String serviceCode, Object rawData){
        this.serviceCode = serviceCode;
        setRawData(rawData);
    }
    
    public Object getRawData(){
        return rawData;
    }
    
    protected void setRawData(Object rawData){
        this.rawData = rawData;
    }
    
    public String getServiceCode(){
        return serviceCode;
    }

    @Override
    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    @Override
    public Object getResponseData() {
        return responseData;
    }
    
}
