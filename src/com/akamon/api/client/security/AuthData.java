package com.akamon.api.client.security;

/**
 *
 * @author Miguel Angel Garcia
 */
public class AuthData {
    
    private String appCode;
    
    private String appToken;
    
    private String format;
    
    public AuthData(String appCode, String appToken, String format){
        setAppCode(appCode);
        setAppToken(appToken);
        setFormat(format);
    }
             
    /**
     * @return the appCode
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * @param appCode the appCode to set
     */
    private void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * @return the appToken
     */
    public String getAppToken() {
        return appToken;
    }

    /**
     * @param appToken the appToken to set
     */
    private void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    private void setFormat(String format) {
        this.format = format;
    }
    
}
