package com.akamon.api.client.proxy.awakeuser;

import java.util.ArrayList;
import java.util.List;

public class AwakeUsersResponseData {
    private Integer offer;
    
    private ActiveOfferProduct product;
    
    private List<ActiveOfferUser> users = new ArrayList();

    /**
     * @return the offer
     */
    public Integer getOffer() {
        return offer;
    }

    /**
     * @param offer the offer to set
     */
    void setOffer(Integer offer) {
        this.offer = offer;
    }

    /**
     * @return the product
     */
    public ActiveOfferProduct getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    void setProduct(ActiveOfferProduct product) {
        this.product = product;
    }

    /**
     * @return the users
     */
    public List<ActiveOfferUser> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    void setUsers(List<ActiveOfferUser> users) {
        this.users = users;
    }    
}