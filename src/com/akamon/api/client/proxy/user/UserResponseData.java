package com.akamon.api.client.proxy.user;

import java.util.List;

/**
 * Bean to encaosulate the user data  obtained from server
 * @author Miguel Angel Garcia
 */
public class UserResponseData {
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
    
    
}




