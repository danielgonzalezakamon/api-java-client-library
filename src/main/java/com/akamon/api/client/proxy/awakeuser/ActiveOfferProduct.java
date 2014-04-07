package com.akamon.api.client.proxy.awakeuser;

public class ActiveOfferProduct {
    
    private Integer id;
    
    private String name;
    
    private String description;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    void setDescription(String description) {
        this.description = description;
    }        
}
