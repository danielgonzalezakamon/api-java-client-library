package com.akamon.api.client.proxy.product;

/**
 * Response data for the api operation getGameProducts
 * @author Isis Garrido
 */
public class GetGameProductsResponseData {
    
    private Integer productId;
    
    private String productName;
    
    private String productTypeName;
    
    private Integer quantity;
    
    private Double amount;
    
    private Integer currencyCode;
    
    
    GetGameProductsResponseData() {}

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
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productTypeName
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * @param productTypeName the productTypeName to set
     */
    void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
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
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the currencyCode
     */
    public Integer getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }
}
