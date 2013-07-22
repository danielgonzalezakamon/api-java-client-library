/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akamon.api.client.error;

/**
 *
 * @author Miguel Angel Garcia
 */
public class ServiceInvocationException extends ApiClientException {
   private String serviceCode;
   
   public String getServiceCode(){
       return serviceCode;
   }
   
   public ServiceInvocationException(String serviceCode){
       this (serviceCode, null, null);              
   }
   
    public ServiceInvocationException(String serviceCode, Throwable t){
       this (serviceCode, null, t);              
   }
   
   public ServiceInvocationException(String serviceCode, String message, Throwable t){              
       super(message == null ? "Error in the invocation in the service " + serviceCode : message, t);
       
       this.serviceCode = serviceCode;              
   }
}
