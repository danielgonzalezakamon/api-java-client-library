/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.akamon.api.client.proxy.product;

/**
 *
 * @author Isis
 */
public class ProductBean {
    private Integer productId;
    
    private String productName;
    
    private String productTypeName;
    
    private Integer quantity;
    
    private Double amount;
    
    private Integer currencyCode;
    
    
    ProductBean() {}

    /**
     * @return the productId
     */
    public Integer getProductId() {
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
    void setQuantity(Integer quantity) {
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
    void setAmount(Double amount) {
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
    void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }
}
