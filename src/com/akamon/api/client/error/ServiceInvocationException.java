package com.akamon.api.client.error;

/**
 * Class to encapsulate an error produced during the invocation of a remote service
 * @author Miguel Angel Garcia
 */
public class ServiceInvocationException extends ApiClientException {
   private String serviceCode;
   
   /**
    * Gets the service code
    * @return The service code
    */
   public String getServiceCode(){
       return serviceCode;
   }
   
   /**
    * Builds the exception
    * @param serviceCode Operation service code
    */
   public ServiceInvocationException(String serviceCode){
       this (serviceCode, null, null);              
   }
   
   /**
    * Builds the exception
    * @param serviceCode Operation service code
    * @param t Previous generated error
    */
    public ServiceInvocationException(String serviceCode, Throwable t){
       this (serviceCode, null, t);              
   }
   
   /**
    * Builds the exception
    * @param serviceCode Operation service code
    * @param message Error message
    * @param t Previous generated error
    */
   public ServiceInvocationException(String serviceCode, String message, Throwable t){              
       super(message == null ? "Error in the invocation in the service " + serviceCode : message, t);
       
       this.serviceCode = serviceCode;              
   }
}
