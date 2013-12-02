package com.akamon.api.client.proxy.product;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.proxy.game.CreateMatchResponseData;
import com.akamon.api.client.security.AuthData;
import com.akamon.api.client.service.ICallableResponse;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import com.akamon.api.client.service.imp.http.JsonCallableResponse;
import java.util.ArrayList;

/**
 *
 * @author Isis Garrido
 */
public class ProductProxy extends BaseServiceProxy { 
                    
    public ProductProxy (AuthData authData){
        super(authData);
    }
    
    /**
     * Create product transaction and virtual currency transaction if the product is a virtual currency
     * @param productId
     * @param productQuantity
     * @param transactionTypeId
     * @param publicUserId
     * @param countryId
     * @param partnerId
     * @param subplatformId
     * @param gameId
     * @return
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public ProductTransactionResponseData setProductTransaction (Integer productId,
            Integer productQuantity,
            Integer transactionTypeId,
            String publicUserId,
            Integer countryId,
            Integer partnerId,
            Integer subplatformId,
            Integer gameId) throws ServiceDefinitionException, ServiceInvocationException
    {
        
        ProductTransactionResponseData response = null;
        Object[] params = {productId,
            productQuantity,
            transactionTypeId,
            publicUserId,
            countryId,
            partnerId,
            subplatformId,
            gameId};
        
        ICallableResponse res = invoke("setProductTransaction", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            try {
                response = jRes.buildResponseDataObject(ProductTransactionResponseData.class);            
            }
            catch (Exception ex){
                 throw new ServiceInvocationException("setProductTransaction", "Response parse error", ex);
            }
        }
        
        return response;                
    }
    
    /**
     * Create product transaction for an exchange of products
     * The user gets a productId and consume exchangeProductId
     * 
     * @param productId Product that the user gets
     * @param productQuantity How many products the user buys
     * @param exchangeProductId Product that the user consume. Must be negative value
     * @param exchangeQuantity How many exchangeProductId consum for the exchange
     * @param transactionTypeId
     * @param publicUserId
     * @param countryId
     * @param partnerId
     * @param subplatformId
     * @param gameId
     * @return
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public ProductExchangeTransactionResponseData setProductExchangeTransaction (Integer productId,
            Integer productQuantity,
            Integer exchangeProductId,
            Integer exchangeQuantity,
            Integer transactionTypeId,
            String publicUserId,
            Integer countryId,
            Integer partnerId,
            Integer subplatformId,
            Integer gameId) throws ServiceDefinitionException, ServiceInvocationException
    {
        
        ProductExchangeTransactionResponseData response = null;
        Object[] params = {productId,
            productQuantity,
            exchangeProductId,
            exchangeQuantity,
            transactionTypeId,
            publicUserId,
            countryId,
            partnerId,
            subplatformId,
            gameId};
        
        ICallableResponse res = invoke("setProductExchangeTransaction", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            
            try {
                response = jRes.buildResponseDataObject(ProductExchangeTransactionResponseData.class);            
            }
            catch (Exception ex){
                 throw new ServiceInvocationException("setProductExchangeTransaction", "Response parse error", ex);
            }
        }
        
        return response;                
    }
    
    /**
     * Get every product related with a game
     * 
     * @param gameId
     * @return
     * @throws ServiceDefinitionException
     * @throws ServiceInvocationException 
     */
    public ArrayList getGameProducts (Integer gameId) 
            throws ServiceDefinitionException, ServiceInvocationException
    {
        //GetGameProductsResponseData response = null;
        ArrayList response = null;
        Object[] params = {gameId};
        
        ICallableResponse res = invoke("getGameProducts", params);
        if (res instanceof JsonCallableResponse){
            JsonCallableResponse jRes = (JsonCallableResponse) res;
            System.out.println(res.getResponseData());
            try {
                response = jRes.buildResponseDataList(GetGameProductsResponseData.class);
                System.out.println(response);
            }
            catch (Exception ex){
                 throw new ServiceInvocationException("getGameProducts", "Response parse error", ex);
            }
        }
        
        return response;
    }
}
