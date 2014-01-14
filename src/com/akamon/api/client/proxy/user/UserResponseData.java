package com.akamon.api.client.proxy.user;

import java.util.Iterator;
import java.util.List;

/**
 * Bean to encapsulate the user data  obtained from server
 * @author Miguel Angel Garcia
 */
public class UserResponseData {
    
    public final static String PROVIDER_FACEBOOK = "facebook";
    public final static String PROVIDER_TARINGA = "taringa";
    public final static String PROVIDER_SPILGAMES = "spilgames";
    public final static String PROVIDER_ORANGE = "orange";    
    
    /**
     * Public user identifier to use in all the calls
     */
    private String public_user_id;
    
    /**
     * User name
     */
    private String user_name;
    
    /**
     * Private user id (for internal use only)
     */
    private Integer user_id;
    
    /**
     * Avatar to obtain the user url
     */
    private String avatar_url;
    
    /**
     * User genre: V for man, H for woman, or I for unknown
     */
    private String genre;
    
    /**
     * User chips balance
     */
    private Integer chips;
    
    /**
     * User links to external providers (for example: facebook, taringa, etc)
     */
    private List<UserLinkFromProviderResponseData> links_from_providers;

    /**
     * @return the public_user_id
     */
    public String getPublic_user_id() {
        return public_user_id;
    }

    /**
     * @param public_user_id Public user identifier to use in all the calls to set
     */
    void setPublic_user_id(String public_user_id) {
        this.public_user_id = public_user_id;
    }

    /**
     * @return User name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name User name
     */
    void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @return Private user id (intern usage only)
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @param user_id Private user id (intern usage only)
     */
    void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @return rivate user id (intern usage only)
     */
    public String getAvatar_url() {
        return avatar_url;
    }

    /**
     * @param avatar_url User avatar_url
     */
    void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    /**
     * @return User genre (V for man, H for woman, and I when is not known)
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre User genre (V for man, H for woman, and I when is not known)
     */
    void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return User chips balance
     */
    public Integer getChips() {
        return chips;
    }

    /**
     * @param User chips balance
     */
    void setChips(Integer chips) {
        this.chips = chips;
    }  

    /**
     * @return the links_from_providers
     */
    public List<UserLinkFromProviderResponseData> getLinks_from_providers() {
        return links_from_providers;
    }

    /**
     * @param links_from_providers the links_from_providers to set
     */
    void setLinks_from_providers(List<UserLinkFromProviderResponseData> links_from_providers) {
        this.links_from_providers = links_from_providers;
    }
    
    /**
     * Get facebook external user id
     * @return 
     */
    public String getFacebookUserId(){
        return getProviderUserId(PROVIDER_FACEBOOK);
    }
    
    /**
     * Get taringa external user id
     * @return 
     */
    public String getTaringaUserId(){
        return getProviderUserId(PROVIDER_TARINGA);
    }
    
    /**
     * Get spilgames external user id
     * @return 
     */
    public String getSpilgamesUserId(){
        return getProviderUserId(PROVIDER_SPILGAMES);
    }
    
    /**
     * Get orange external user id
     * @return 
     */
    public String getOrangeUserId(){
        return getProviderUserId(PROVIDER_SPILGAMES);
    }        
    
    /**
     * Obtains the user id that matches with the provider
     * @param provider
     * @return 
     */
    private String getProviderUserId(String provider){
        String providerUserId = null;
        
        Iterator<UserLinkFromProviderResponseData> linkedDataIterator = this.links_from_providers.iterator();                 
        boolean keepSearchActive = true;
        
        do {
            providerUserId = getExternalUserIdIfProviderMatches(provider, linkedDataIterator.next());
            keepSearchActive = providerUserIdFoundOrSearchCollectionNotEmpty(providerUserId, linkedDataIterator);
        }
        while (keepSearchActive);
        
        return providerUserId;
    }  
    
    /**
     * Checks if the user id has been found, or we have to keep the serach on
     * @param currentProviderId
     * @param linkedDataIterator
     * @return 
     */
    private boolean providerUserIdFoundOrSearchCollectionNotEmpty(
            String currentProviderId, 
            Iterator<UserLinkFromProviderResponseData> linkedDataIterator)
    {
        return ((linkedDataIterator.hasNext()) && (currentProviderId == null));
    }
    
    /**
     * Gets the user id from the provider, is the linked data corresponfs to the right provider
     * @param provider
     * @param linkedData
     * @return 
     */
    private String getExternalUserIdIfProviderMatches(String provider, UserLinkFromProviderResponseData linkedData){
        String providerUserId = null;
        
        if (linkedData.getProvider().equals(provider)){
            providerUserId = linkedData.getProvider_user_id();
        }
        
        return providerUserId;
    }
}




