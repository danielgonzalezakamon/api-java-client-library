/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akamon.api.client.net.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class InvalidHttpUrlException extends HttpRequestException {
     
    private String url;
    
    public InvalidHttpUrlException(String url){
         super("Invalid url " + url);
         setUrl(url);
     }    

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    private void setUrl(String url) {
        this.url = url;
    }
}
