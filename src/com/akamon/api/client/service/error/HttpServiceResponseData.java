package com.akamon.api.client.service.error;

/**
 *
 * @author miguelgarcia
 */
public class HttpServiceResponseData {

    private int httpResponseCode;
  
    private String httpResponseFormat;
    
    private String httpResponseBody;
    
    private String serviceCode;
    
    private int serviceErrorCode;
    
    private String serviceErrorString; 

    /**
     * @return the httpResponseCode
     */
    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    /**
     * @param httpResponseCode the httpResponseCode to set
     */
    public void setHttpResponseCode(int httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    /**
     * @return the httpResponseFormat
     */
    public String getHttpResponseFormat() {
        return httpResponseFormat;
    }

    /**
     * @param httpResponseFormat the httpResponseFormat to set
     */
    public void setHttpResponseFormat(String httpResponseFormat) {
        this.httpResponseFormat = httpResponseFormat;
    }

    /**
     * @return the httpResponseBody
     */
    public String getHttpResponseBody() {
        return httpResponseBody;
    }

    /**
     * @param httpResponseBody the httpResponseBody to set
     */
    public void setHttpResponseBody(String httpResponseBody) {
        this.httpResponseBody = httpResponseBody;
    }

    /**
     * @return the serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode the serviceCode to set
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * @return the serviceErrorCode
     */
    public int getServiceErrorCode() {
        return serviceErrorCode;
    }

    /**
     * @param serviceErrorCode the serviceErrorCode to set
     */
    public void setServiceErrorCode(int serviceErrorCode) {
        this.serviceErrorCode = serviceErrorCode;
    }

    /**
     * @return the serviceErrorString
     */
    public String getServiceErrorString() {
        return serviceErrorString;
    }

    /**
     * @param serviceErrorString the serviceErrorString to set
     */
    public void setServiceErrorString(String serviceErrorString) {
        this.serviceErrorString = serviceErrorString;
    }            
}
