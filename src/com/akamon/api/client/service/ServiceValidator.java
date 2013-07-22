package com.akamon.api.client.service;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Angel Garcia
 */
public class ServiceValidator {
            
    public void validate(String serviceCode, Object[] data) throws ServiceDefinitionException, ServiceInvocationException {
        try {
            ServiceParameterValidator validator = new ServiceParameterValidator(serviceCode);
            
            ServiceConfigManager manager = ServiceConfigManager.newInstance(serviceCode);

            HashMap<String,Object> parameterDefinitions = (HashMap<String,Object>) manager.getConfigParameter("params");            
            int paramNumber = -1;
            for (String parameter: parameterDefinitions.keySet()){                            
                
                Object value = null;
                
                if ( ++paramNumber < data.length ){
                    value = data[paramNumber];                     
                }     
                
                HashMap<String,Object> parameterDefinition = (HashMap<String,Object>) parameterDefinitions.get(parameter);
                
                validator.validate(parameter, value, parameterDefinition);  
            }                                             
        }
        catch (ServiceDefinitionException sde){
            throw sde;
        }
        catch (ServiceInvocationException sie){
            throw sie;
        }
        catch (Exception e){
            throw new ServiceInvocationException(serviceCode, e);
        }                      
    }
    
}
