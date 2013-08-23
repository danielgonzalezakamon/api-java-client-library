package com.akamon.api.client.service.imp.http;

import com.akamon.api.client.service.BaseCallableResponse;
import com.akamon.api.client.service.imp.http.error.BadJsonResponseInvocationException;
import com.akamon.api.client.util.JsonUtility;
import com.google.gson.internal.LinkedTreeMap;

/**
 *
 * @author Miguel Angel Garcia
 */
public class JsonCallableResponse extends BaseCallableResponse {
              
    private JsonCallableResponseBean jsonData;
    
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

    @Override
    public int getErrorCode() {
        return jsonData == null ? 0 : jsonData.getErrorCode();
    }

    @Override
    public String getErrorString() {
        return jsonData == null ? "" : jsonData.getErrorString();
    }
    
    public Integer getResponseIntegerValue(String key) {
        Object oResponseData = getResponseData();
        Integer value = null;

        if (oResponseData instanceof LinkedTreeMap) {
            LinkedTreeMap data = (LinkedTreeMap) getResponseData();
            if (data.containsKey(key)) {
                Object oMatch = data.get(key);

                if (oMatch != null) {
                    if (oMatch instanceof Double) {
                        value = ((Double) oMatch).intValue();
                    } else if (oMatch instanceof Integer) {
                        value = (Integer) oMatch;
                    } else {
                        value = Integer.parseInt(data.get(key).toString());
                    }
                }
            }
        }
        return value;
    }
    
}
