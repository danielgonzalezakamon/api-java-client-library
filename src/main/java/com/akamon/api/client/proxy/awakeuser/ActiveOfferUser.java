package com.akamon.api.client.proxy.awakeuser;

public class ActiveOfferUser {
    
    private String publicUserId;
    
    private String name;

    /**
     * @return the publicUserId
     */
    public String getPublicUserId() {
        return publicUserId;
    }

    /**
     * @param publicUserId the publicUserId to set
     */
    void setPublicUserId(String publicUserId) {
        this.publicUserId = publicUserId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    void setName(String name) {
        this.name = name;
    }    
}
