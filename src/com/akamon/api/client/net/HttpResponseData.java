package com.akamon.api.client.net;

/**
 * Class responsible for encapsulating the response obtained from the api rest server
 * @author Miguel Angel Garcia
 */
class HttpResponseData {
    private int responseCode = 0;
    
    private String responseString = "";
    
    /**
     * Builds the object
     * @param responseCode Http response code (200 (OK), 500 (internal server error)), etc 
     * @param responseString Response content retrieved from server 
     */
    HttpResponseData(int responseCode, String responseString){
        this.responseCode = responseCode;
        this.responseString = responseString;
    }
    
    /**
     * Gets the http response code
     * @return 
     */
    public int getResponseCode(){
        return responseCode;
    }
    
    /**
     * Gets the response content retrieved from the server
     * @return 
     */
    public String getResponseString(){
        return responseString;
    }       
}
