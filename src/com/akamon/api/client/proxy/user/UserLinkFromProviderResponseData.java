package com.akamon.api.client.proxy.user;

/**
 * Bean class that encapsulates the portion of response obtained from server
 * tha represents that a user, is linked to a external provider. For example,
 * in the case of facebook, can be used to obtained the associated facebook user
 * id (if the account is linked to facebook)
 * 
 * @author miguelgarcia
 */
class UserLinkFromProviderResponseData {
    
    UserLinkFromProviderResponseData(){}
    
    /**
     * provider name, for example 'facebook'
     */
    private String provider;
    
    /**
     * id of the user in the external system, for example, facebook_user_id
     */
    private String provider_user_id;

    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * @return the provider_user_id
     */
    public String getProvider_user_id() {
        return provider_user_id;
    }

    /**
     * @param provider_user_id the provider_user_id to set
     */
    void setProvider_user_id(String provider_user_id) {
        this.provider_user_id = provider_user_id;
    }    
    
    @Override
    public String toString(){
        StringBuilder serializer = new StringBuilder("UserLinkedToProviderData{ provider:'")
                .append(provider)
                .append("', provider_user_id: '")
                .append(provider_user_id)
                .append("'}");
        
        return serializer.toString();
    }
}
