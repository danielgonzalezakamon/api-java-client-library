package com.akamon.api.client.service.imp.http;

import com.akamon.api.client.service.BaseCallableResponse;
import com.akamon.api.client.service.imp.http.error.BadJsonResponseInvocationException;
import com.akamon.api.client.util.JsonUtility;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that encapsulates a json response obtained from the rest api service
 * @author Miguel Angel Garcia
 */
public class JsonCallableResponse extends BaseCallableResponse {
              
    private JsonCallableResponseBean jsonData;
    
    /**
     * Builds the object
     * @param serviceCode Operation service code
     * @param rawData Raw data obtained from server
     * @throws Exception 
     */
    public JsonCallableResponse(String serviceCode, Object rawData) throws Exception{
        super(serviceCode, rawData);
        
        String jsonString = (rawData == null) ? "" : rawData.toString();
        
        if (jsonString.equals("")) {
            throw new BadJsonResponseInvocationException(serviceCode, jsonString);
        }
                        
        JsonUtility json = new JsonUtility();
        jsonData = json.fromJson(jsonString, JsonCallableResponseBean.class);
        
        if ( (jsonData == null) || (jsonData.getErrorCode() == null) || (jsonData.getErrorString() == null) 
                || (jsonData.getResponseData() == null)  || (!(jsonData.getResponseData() instanceof com.google.gson.internal.LinkedTreeMap))  )
        {
            throw new BadJsonResponseInvocationException(serviceCode, jsonString);
        }  
        
        setResponseData(jsonData.getResponseData());        
    }        

    /**
     * Gets the error code (0 if everything went ok)
     * @return Error code (0 if everything went ok)
     */
    @Override
    public int getErrorCode() {
        return jsonData == null ? 0 : jsonData.getErrorCode();
    }

    /**
     * Gets the error string ("" if everything went ok)
     * @return Error string ("" if everything went ok)
     */
    @Override
    public String getErrorString() {
        return jsonData == null ? "" : jsonData.getErrorString();
    }        
    
    /**
     * Parses and builds the specific response data object     
     * @param destinationClass Class of the right response object
     * @return Response object
     */
    public <T>T buildResponseDataObject(Class <? extends T> destinationClass)
    throws JsonParseException, JsonSyntaxException
    {
        JsonUtility json = new JsonUtility();                 
         
        String jsonRawResData = json.toJson(getResponseData());               
        return json.fromJson(jsonRawResData, destinationClass);
        
    }
    
}