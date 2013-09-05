package com.akamon.api.client.service;

import com.akamon.api.client.service.error.InvalidServiceDefinitionFileException;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 * Class to manage loading the service config data.
 * Important: you <b>must</b> call to registerConfigDir() at the beggining of your application
 * @author Miguel Angel Garcia
 */
public class ServiceConfigManager {            
    private static String configDir = null;
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
     * Gets the conf directory (where are the config data files)
     * @return conf directory 
     */
    public static String getConfigDir(){
        return configDir;
    }
    
    /**
     * Sets the conf directory (where are the config data files)
     * @param configDir conf directory
     */
    public static void registerConfigDir(String configDir){
        ServiceConfigManager.configDir = configDir;
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
            manager = new ServiceConfigManager(serviceCode, ServiceConfigManager.getConfigDir());
            ServiceConfigManager.addServiceConfigurationManager(manager);
        }        
        
        return manager;                
    }     
    
    private String dir = null;
    private String serviceCode = null;
    private Map<String, Object> fileContent = null;
    
    /**
     * Builds the object
     * @param serviceCode Operation service code
     * @param dir Directory where the config files are
     * @throws ServiceDefinitionException 
     */
    private ServiceConfigManager(String serviceCode, String dir) throws ServiceDefinitionException {
        this.serviceCode = serviceCode;
        this.dir = dir;
        Map<String, Object> content = null;
               
        content = loadYmlConfigFile(this.serviceCode);
                        
        if (content.containsKey(this.serviceCode)){            
            fileContent = (java.util.HashMap) content.get(this.serviceCode);
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
     * @return the dir
     */
    public String getDir() {
        return dir;
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
        String fichero = "";
        try {
            fichero = builRouteToConfigFile(serviceCode);
            Yaml yaml = new Yaml();
            return (Map<String, Object>) yaml.load(new BufferedInputStream(new FileInputStream(fichero)));
        }
        catch (FileNotFoundException fnfe){
            throw new InvalidServiceDefinitionFileException(serviceCode, fichero);
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
    private String builRouteToConfigFile(String serviceCode){
        return this.dir + java.io.File.separator + serviceCode + ".yml";
    }
    
    /**
     * Gets the value for the given config key
     * @param key
     * @return Config parameter data
     */
    public Object getConfigParameter(String key) {
        Object param = null;
        
        if (fileContent.containsKey(key)){            
            return fileContent.get(key);
        }           
        
        return param;
    }    
    
}
