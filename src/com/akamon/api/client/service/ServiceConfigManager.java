/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Miguel Angel Garcia
 */
public class ServiceConfigManager {            
    private static String configDir = null;
    private static HashMap<String,ServiceConfigManager> configManagers;
    
    static {
        reloadAllServiceConfigurationManagers();
    }
    
    public static void reloadAllServiceConfigurationManagers(){
        ServiceConfigManager.configManagers = new HashMap<String,ServiceConfigManager>();
    }
    
    public static void reloadServiceConfigurationManager(String serviceCode){
        if (ServiceConfigManager.configManagers.containsKey(serviceCode)){
            ServiceConfigManager.configManagers.remove(serviceCode);
        }
    }
    
    private static void addServiceConfigurationManager(ServiceConfigManager manager){
        if (manager != null){
            ServiceConfigManager.reloadServiceConfigurationManager(manager.getServiceCode());
            ServiceConfigManager.configManagers.put(manager.getServiceCode(), manager);
        }
    }
    
    private static ServiceConfigManager getServiceConfigurationManager(String serviceCode){
        ServiceConfigManager manager = null;
        
        if (ServiceConfigManager.configManagers.containsKey(serviceCode)){
            manager = ServiceConfigManager.configManagers.get(serviceCode);
        } 
        
        return manager;
    }
    
    public static String getConfigDir(){
        return configDir;
    }
    
    public static void registerConfigDir(String configDir){
        ServiceConfigManager.configDir = configDir;
    }        
    
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
    
    private String builRouteToConfigFile(String serviceCode){
        return this.dir + java.io.File.separator + serviceCode + ".yml";
    }
    
    public Object getConfigParameter(String key) {
        Object param = null;
        
        if (fileContent.containsKey(key)){            
            return fileContent.get(key);
        }           
        
        return param;
    }    
    
}
