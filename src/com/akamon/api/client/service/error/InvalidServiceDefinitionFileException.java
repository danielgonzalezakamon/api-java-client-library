package com.akamon.api.client.service.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class InvalidServiceDefinitionFileException extends ServiceDefinitionException {
    
    private String file = null;
    
    public InvalidServiceDefinitionFileException (String serviceCode, String file){
        this(serviceCode, file, null);
    }
    
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
