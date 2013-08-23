package com.akamon.api.client.service.error;

/**
 * Encapsulates an error produced by ain incorect service definition file
 * @author Miguel Angel Garcia
 */
public class InvalidServiceDefinitionFileException extends ServiceDefinitionException {
    
    private String file = null;
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param file Service config file
     */
    public InvalidServiceDefinitionFileException (String serviceCode, String file){
        this(serviceCode, file, null);
    }
    
    /**
     * Builds the exception
     * @param serviceCode Operation service code
     * @param file Service config file
     * @param t Previous generated error
     */
    public InvalidServiceDefinitionFileException (String serviceCode, String file, Throwable t){
        super(serviceCode, t);
        setFile(file);
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    private void setFile(String file) {
        this.file = file;
    }
    
}
