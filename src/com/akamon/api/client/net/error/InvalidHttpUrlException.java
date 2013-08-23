package com.akamon.api.client.net.error;

/**
 * Encapsulates an error produced by an inavlid http url
 * @author Miguel Angel Garcia
 */
public class InvalidHttpUrlException extends HttpRequestException {
     
    private String url;
    
    /**
     * Builds the exception
     * @param url Invalid url
     */
    public InvalidHttpUrlException(String url){
         super("Invalid url " + url);
         setUrl(url);
     }    

    /**
     * Gets the url
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url
     * @param url the url to set
     */
    private void setUrl(String url) {
        this.url = url;
    }
}
