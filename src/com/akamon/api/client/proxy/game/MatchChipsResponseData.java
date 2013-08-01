/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akamon.api.client.proxy.game;

/**
 *
 * @author Miguel Angel Garcia
 */
public class MatchChipsResponseData {
    private String publicUserId = null;
    
    private Integer chips = null;

    /**
     * @return the publicUserId
     */
    public String getPublicUserId() {
        return publicUserId;
    }

    /**
     * @param publicUserId the publicUserId to set
     */
    public void setPublicUserId(String publicUserId) {
        this.publicUserId = publicUserId;
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
