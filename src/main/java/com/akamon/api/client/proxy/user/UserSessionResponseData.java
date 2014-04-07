package com.akamon.api.client.proxy.user;

/**
 * User session response data object
 * (response for operation createUserSession)
 * 
 * @author Miguel Angel Garcia
 */
public class UserSessionResponseData {
   private String session_id;

    /**
     * @return the session_id
     */
    public String getSession_id() {
        return session_id;
    }

    /**
     * @param session_id the session_id to set
     */
    void setSession_id(String session_id) {
        this.session_id = session_id;
    }      
   
}
