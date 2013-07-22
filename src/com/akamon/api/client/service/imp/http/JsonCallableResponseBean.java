package com.akamon.api.client.service.imp.http;

/***
 * 
 *  {
    "errorCode" : 0,
    "errorString": "",
    "responseData" : {"session_id":"8e1f1c9fb5446729b6d8fbea3a4eef8a"}}
*/

/**
 *
 * @author Miguel Angel Garcia
 */
public class JsonCallableResponseBean {
    private Integer errorCode;
    private String errorString;
    private Object responseData;

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorString
     */
    public String getErrorString() {
        return errorString;
    }

    /**
     * @param errorString the errorString to set
     */
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    /**
     * @return the responseData
     */
    public Object getResponseData() {
        return responseData;
    }

    /**
     * @param responseData the responseData to set
     */
    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
    
    
    
}
