package com.akamon.api.client.proxy.product;

import java.util.List;

/**
 * Response data for the api operation getGameProducts
 * @author Isis Garrido
 */
public class GetGameProductsResponseData {
    
    private List<ProductBean> products;

    /**
     * @return the product
     */
    public List<ProductBean> getProducts() {
        return products;
    }

    /**
     * @param product the product to set
     */
    void setProducts(List<ProductBean> products) {
        this.products = products;
    }
}
