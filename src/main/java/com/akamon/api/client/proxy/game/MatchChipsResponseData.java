package com.akamon.api.client.proxy.game;

/**
 * Bean to encaosulate the response obtained from server to the matchChips operation
 * @author Miguel Angel Garcia
 */
public class MatchChipsResponseData {
    private String public_user_id = null;
    
    private Integer chips = null;

    /**
     * @return the public_user_id
     */
    public String getPublicUserId() {
        return public_user_id;
    }

    /**
     * @param publicUserId the public_user_id to set
     */
    public void setPublicUserId(String publicUserId) {
        this.public_user_id = publicUserId;
    }

    /**
     * @return the chips
     */
    public Integer getChips() {
        return chips;
    }

    /**
     * @param chips the chips to set
     */
    public void setChips(Integer chips) {
        this.chips = chips;
    }
}
