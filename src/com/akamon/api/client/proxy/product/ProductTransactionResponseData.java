package com.akamon.api.client.proxy.product;

/**
 * Response data for the api operation setProductTransaction
 * @author Isis Garrid
 */
public class ProductTransactionResponseData {
    
    private Integer productId;
    
    private Integer productQuantity;
    
    private Integer transactionTypeId;
    
    private String publicUserId;
    
    private Integer countryId;
    
    private Integer partnerId;
    
    private Integer subplatformId;
    
    private Integer gameId;
    
    ProductTransactionResponseData() {}

    /**
     * @return the productId
     */
    Integer getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the productQuantity
     */
    public Integer getProductQuantity() {
        return productQuantity;
    }

    /**
     * @param productQuantity the productQuantity to set
     */
    void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * @return the transactionTypeId
     */
    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    /**
     * @param transactionTypeId the transactionTypeId to set
     */
    void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

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
     * @return the countryId
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the partnerId
     */
    public Integer getPartnerId() {
        return partnerId;
    }

    /**
     * @param partnerId the partnerId to set
     */
    void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * @return the subplatformId
     */
    public Integer getSubplatformId() {
        return subplatformId;
    }

    /**
     * @param subplatformId the subplatformId to set
     */
    void setSubplatformId(Integer subplatformId) {
        this.subplatformId = subplatformId;
    }

    /**
     * @return the gameId
     */
    public Integer getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
    
    
    
}
