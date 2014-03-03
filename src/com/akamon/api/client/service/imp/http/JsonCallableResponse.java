package com.akamon.api.client.service.imp.http;

import com.akamon.api.client.service.BaseCallableResponse;
import com.akamon.api.client.service.imp.http.error.BadJsonResponseInvocationException;
import com.akamon.api.client.util.JsonUtility;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

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
                        
        JsonUtility json = new JsonUtility();
        jsonData = json.fromJson(jsonString, JsonCallableResponseBean.class);
                
        checkJsonData(serviceCode, jsonString);
        
        setResponseData(jsonData.getResponseData());        
    }  
    
    private void checkJsonData(String serviceCode, String jsonString) throws BadJsonResponseInvocationException {
        boolean notValidJson = !isValidJsonData();
        if (notValidJson){
            throw new BadJsonResponseInvocationException(serviceCode, jsonString   );
        }        
    }
    
    private boolean isValidJsonData(){
        Integer errorCode = null;
        String errorString = null;
        Object responseData = null;
        boolean dataWasParsed = (jsonData != null );
        
        if (dataWasParsed){
            errorCode = jsonData.getErrorCode();
            errorString = jsonData.getErrorString();
            responseData = jsonData.getResponseData();
        }
                        
        boolean definedErrorCode = (errorCode != null);
        boolean definedErrorString = (errorString != null);
        boolean definedResponseData = (responseData != null);      
        
        boolean validResponse = ( (definedErrorCode) && (definedErrorString) && (definedResponseData) ); 
        
        return validResponse;
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
