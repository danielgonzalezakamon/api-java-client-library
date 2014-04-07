package com.akamon.api.client.util;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

/**
 * Utility to handle the goole json library (gson)
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
    
    public <T>T fromJson(String jsonString, Class <? extends T> destinationClass ) throws JsonParseException, JsonSyntaxException
    {
        return gson.fromJson(jsonString, destinationClass);   
        
    }
}
