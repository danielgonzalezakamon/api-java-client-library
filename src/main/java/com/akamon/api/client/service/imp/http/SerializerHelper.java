package com.akamon.api.client.service.imp.http;

import com.akamon.api.client.util.JsonUtility;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Object responsible for managing the data conversion before the http invocation
 * @author Miguel Angel Garcia
 */
public class SerializerHelper {
    private DateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
    
    /**
     * Serializes the given data in a way the akamon service can understand
     * @param data Data to serialize
     * @return The serialized data
     */
    public String serialize(Object data){
        String serialized = "";
        
        if (data != null){
             
            boolean customSerialization = dataNeedsCustomSerialization(data);
                        
            if ((customSerialization) && (data instanceof java.util.Date)) {
                serialized = df.format((java.util.Date) data);
            }
            else if (customSerialization)  {
                JsonUtility jsonUt = new JsonUtility();
                serialized = jsonUt.toJson(data);                
            }
            else {
                serialized = data.toString();
            }
        }                
        
        return serialized;
	}

	private boolean dataNeedsCustomSerialization(Object data) {
		return (data instanceof Object[]) || (data instanceof java.util.List) || (data instanceof java.util.HashMap) || (data instanceof java.util.Date);
	}
}
