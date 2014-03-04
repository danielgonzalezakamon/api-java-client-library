package com.akamon.api.client.error;

/**
 *
 * @author miguelgarcia
 */
public class ServiceInternalError extends ServiceInvocationException {
    
    private int serviceErrorCode;
    private String serviceErrorString;
    
    public ServiceInternalError(String serviceCode, int serviceErrorCode, String serviceErrorString, Throwable t){
        super(serviceCode, 
                "An internal server error has been produced executing the service " + serviceCode + 
                        "\nError(" + serviceErrorCode + "): " + serviceErrorString, null);
        
        this.serviceErrorCode = serviceErrorCode;
        this.serviceErrorString = serviceErrorString;
    }  
    
    public ServiceInternalError(String serviceCode, int serviceErrorCode, String serviceErrorString){        
        this(serviceCode, serviceErrorCode, serviceErrorString, null);
    }

    /**
     * @return the serviceErrorCode
     */
    public int getServiceErrorCode() {
        return serviceErrorCode;
    }

    /**
     * @param serviceErrorCode the serviceErrorCode to set
     */
    public void setServiceErrorCode(int serviceErrorCode) {
        this.serviceErrorCode = serviceErrorCode;
    }

    /**
     * @return the serviceErrorString
     */
    public String getServiceErrorString() {
        return serviceErrorString;
    }

    /**
     * @param serviceErrorString the serviceErrorString to set
     */
    public void setServiceErrorString(String serviceErrorString) {
        this.serviceErrorString = serviceErrorString;
    }        
    
}
