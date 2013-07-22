package com.akamon.api.client.util;

import com.google.gson.Gson;

/**
 *
 * @author Miguel Angel Garcia
 */
public class JsonUtility {
    Gson gson;
    
    public JsonUtility(){
        gson = new Gson();
    }
    
    public String toJson(Object obj){
        return gson.toJson(obj);
    }
    
    public <T>T fromJson(String jsonString, Class <? extends T> destinationClass ) throws Exception
    {
        return gson.fromJson(jsonString, destinationClass);                
    }
}
