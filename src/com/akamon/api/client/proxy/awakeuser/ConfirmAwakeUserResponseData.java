package com.akamon.api.client.proxy.awakeuser;

public class ConfirmAwakeUserResponseData {
    
    private ActiveOfferProduct product;
    
    private Integer quantity;

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
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }        
}
