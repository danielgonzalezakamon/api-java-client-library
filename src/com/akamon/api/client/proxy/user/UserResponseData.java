package com.akamon.api.client.proxy.user;

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
     * @return the public_user_id
     */
    public String getPublic_user_id() {
        return public_user_id;
    }

    /**
     * @param public_user_id Public user identifier to use in all the calls to set
     */
    public void setPublic_user_id(String public_user_id) {
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
    public void setUser_name(String user_name) {
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
    public void setUser_id(Integer user_id) {
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
    public void setAvatar_url(String avatar_url) {
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
    public void setGenre(String genre) {
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
    public void setChips(Integer chips) {
        this.chips = chips;
    }        
}




