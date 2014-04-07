package com.akamon.api.client.service;

import com.akamon.api.client.service.error.InvalidServiceDefinitionFileException;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 * Class to manage loading the service config data.
 * Important: you <b>must</b> call to registerConfigDir() at the beggining of your application
 * @author Miguel Angel Garcia
 */
public class ServiceConfigManager {            
  
    private static HashMap<String,ServiceConfigManager> configManagers;
    
    static {
        reloadAllServiceConfigurationManagers();
    }
    
    /**
     * Invalidates all the service config data
     */
    public static void reloadAllServiceConfigurationManagers(){
        ServiceConfigManager.configManagers = new HashMap<String,ServiceConfigManager>();
    }
    
    /**
     * Reload the loaded config, data but only for the serviceCode operation
     * @param serviceCode Operation service code to reload its data
     */
    public static void reloadServiceConfigurationManager(String serviceCode){
        if (ServiceConfigManager.configManagers.containsKey(serviceCode)){
            ServiceConfigManager.configManagers.remove(serviceCode);
        }
    }
    
    /**
     * Adds a configuration manager, to handle the data for a specific serviceCode operation
     * @param manager 
     */
    private static void addServiceConfigurationManager(ServiceConfigManager manager){
        if (manager != null){
            ServiceConfigManager.reloadServiceConfigurationManager(manager.getServiceCode());
            ServiceConfigManager.configManagers.put(manager.getServiceCode(), manager);
        }
    }
    
    /**
     * Gets the right manager to handle the configuration data for the serviceCode operation
     * @param serviceCode Operation service code
     * @return The service config manager to handle the config for the service
     */
    private static ServiceConfigManager getServiceConfigurationManager(String serviceCode){
        ServiceConfigManager manager = null;
        
        if (ServiceConfigManager.configManagers.containsKey(serviceCode)){
            manager = ServiceConfigManager.configManagers.get(serviceCode);
        } 
        
        return manager;
    }                      
    
    /**
     * <<Singleton>> Loads a new instance of the class
     * @param serviceCode Operation service code
     * @return A new config manager
     * @throws ServiceDefinitionException 
     */
    public static ServiceConfigManager newInstance(String serviceCode) throws ServiceDefinitionException {
        
        ServiceConfigManager manager = ServiceConfigManager.getServiceConfigurationManager(serviceCode);                
        
        if (manager == null){
            manager = new ServiceConfigManager(serviceCode);
            ServiceConfigManager.addServiceConfigurationManager(manager);
        }        
        
        return manager;                
    }     
        
    private String serviceCode = null;
    private Map<String, Object> serviceDefinitionContent = null;
    
    /**
     * Builds the object
     * @param serviceCode Operation service code     
     * @throws ServiceDefinitionException 
     */
    private ServiceConfigManager(String serviceCode) throws ServiceDefinitionException {
        this.serviceCode = serviceCode;
       
        Map<String, Object> content = null;
               
        content = loadYmlConfigFile(this.serviceCode);
                        
        if (content.containsKey(this.serviceCode)){            
            serviceDefinitionContent = (java.util.HashMap) content.get(this.serviceCode);
        }        
        else {
            // Invalid Yml... throw exception
            throw new ServiceDefinitionException(serviceCode);
        }
        
        // Validate the params key
        if ( getConfigParameter("params") == null ){
            throw new ServiceDefinitionException(serviceCode);
        }
        
    }    

    /**
     * @return the serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }
    
    /**
     * Loads the config data from file
     * @param serviceCode Operation service code
     * @return Config data
     * @throws ServiceDefinitionException 
     */
    private Map<String, Object> loadYmlConfigFile(String serviceCode) throws ServiceDefinitionException {
        String resourceName = "";
        try {
            resourceName = builResourceRouteToConfigFile(serviceCode);
            InputStream serviceDefintionStream = this.getClass().getResourceAsStream(resourceName);
            
            if (serviceDefintionStream == null) throw new NullPointerException();
                        
            Yaml yaml = new Yaml();
            return (Map<String, Object>) yaml.load(new BufferedInputStream(serviceDefintionStream));
        }
        catch (NullPointerException npe){
            throw new InvalidServiceDefinitionFileException(serviceCode, resourceName);
        }
        catch (Exception e){
            throw new ServiceDefinitionException(serviceCode, e);            
        }
    }
    
    /**
     * Generates the path to the right config file
     * @param serviceCode
     * @return Generates the expected route to load the service config data
     */
    private String builResourceRouteToConfigFile(String serviceCode){
        StringBuilder resourceRouteBuilder = new StringBuilder("definition/");
        resourceRouteBuilder.append(serviceCode).append(".yml");
        
        String resourceRoute = resourceRouteBuilder.toString();
               
        return resourceRoute;
    }
    
    /**
     * Gets the value for the given config key
     * @param key
     * @return Config parameter data
     */
    public Object getConfigParameter(String key) {
        Object param = null;
        
        if (serviceDefinitionContent.containsKey(key)){            
            return serviceDefinitionContent.get(key);
        }           
        
        return param;
    }    
    
}
