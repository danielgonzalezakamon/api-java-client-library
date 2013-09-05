package com.akamon.api.client.async;

import com.akamon.api.client.proxy.BaseServiceProxy;
import com.akamon.api.client.service.error.ServiceDefinitionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class (thread) to invoque asynchronously operations
 * @author Miguel Angel Garcia
 */
public class AsyncInvocationClient extends Thread{
 
    /**
     * Proxy to invoque
     */
    private BaseServiceProxy serviceProxy;
    
    /**
     * Service code
     */
    private String serviceCode;
    
    /**
     * Invocation params
     */
    private Object[] params;
    
    /**
     * Listener to notify that an operation finished
     */
    private IAsyncCallbackListener listener;
    
    /**
     * Extra data to be passed to the listener
     */
    private Object extraData;
    
    /*
     * Real method from the proxy object to perform the operation
     */
    private Method method;
    
    /**
     * Response obtained from the proxy object after the invocation
     */
    private Object response;
    
    /**
     * Builds the object
     * @param serviceProxy Service proxy object
     * @param serviceCode Service code of the operation to invoke
     * @param params Service params     
     * @throws ServiceDefinitionException If it's not possible to load the service
     */
    public AsyncInvocationClient(BaseServiceProxy serviceProxy, 
            String serviceCode, 
            Object[] params) throws ServiceDefinitionException 
    {
        this(serviceProxy, serviceCode, params, null, null);    
    }
            
    /**
     * Builds the object
     * @param serviceProxy Service proxy object
     * @param serviceCode Service code of the operation to invoke
     * @param params Service params
     * @param listener Listener to notify when the operation finishes
     * @throws ServiceDefinitionException If it's not possible to load the service
     */
    public AsyncInvocationClient(BaseServiceProxy serviceProxy, 
            String serviceCode, 
            Object[] params,
            IAsyncCallbackListener listener) throws ServiceDefinitionException 
    {
        this(serviceProxy, serviceCode, params, listener, null);    
    }
    
    /**
     * Builds the object
     * @param serviceProxy Service proxy object
     * @param serviceCode Service code of the operation to invoke
     * @param params Service params
     * @param listener Listener to notify when the operation finishes
     * @param extraData Extra data to be passed to the listener
     * @throws ServiceDefinitionException If it's not possible to load the service
     */
    public AsyncInvocationClient(BaseServiceProxy serviceProxy, 
            String serviceCode, 
            Object[] params,
            IAsyncCallbackListener listener,
            Object extraData) throws ServiceDefinitionException 
    {
        try {
            setMethod(serviceProxy.getClass().getMethod(serviceCode, getParameterClasses(params)));
        } 
        catch (NoSuchMethodException ex) {
            throw new ServiceDefinitionException(serviceCode, ex);
        } 
        catch (SecurityException ex) {
            throw new ServiceDefinitionException(serviceCode, ex);
        }
        
        setServiceProxy(serviceProxy);
        setServiceCode(serviceCode);
        setParams(params);
        setListener(listener);
        setExtraData(extraData);   
        setResponse(null);
    }        
        
    /**
     * Figured out the class of the parameters
     * @param parameters Array of parameters to be passed to the invocation of the service
     * @return Array of Class, the i-position links to the class of the i-parameter
     */
    private Class[] getParameterClasses(Object... parameters) {
        Class[] classes = new Class[parameters.length];
        for (int i=0; i < classes.length; i++) {
            classes[i] = parameters[i].getClass();
        }
        return classes;
    }
        
    /**
     * Starts the thread, calling to the servive, and notifying the listener (if defined)
     */
    @Override
    public void run(){        
        
        Exception invocationError = null;       
        setResponse(null);
        
        try {
            if (false) throw new Exception("");
            
            // It's a synchronized sectiondue the fact is not guaranteed that the proxy object
            // hasn't been shared along different threads
            synchronized(serviceProxy){
                setResponse(getMethod().invoke(getServiceProxy(), getParams()));
            }
            
        } 
        catch (IllegalAccessException ex) {
            invocationError = new ServiceDefinitionException(getServiceCode(), ex);
        } 
        catch (IllegalArgumentException ex) {
            invocationError = new ServiceDefinitionException(getServiceCode(), ex);
        } 
        catch (InvocationTargetException ex) {
            invocationError = new ServiceDefinitionException(getServiceCode(), ex);
        }
        catch (Exception ex){
            invocationError = ex;
        }
            
        if ( this.listener != null ){
            if (invocationError == null){
                // success
                this.listener.successAction(getServiceCode(), getResponse(), getParams(), getExtraData());
            }
            else {
                // error
                this.listener.errorAction(getServiceCode(), invocationError, getParams(), getExtraData());
            }
        }
    }

    /**
     * @return the serviceProxy
     */
    public BaseServiceProxy getServiceProxy() {
        return serviceProxy;
    }

    /**
     * @param serviceProxy the serviceProxy to set
     */
    private void setServiceProxy(BaseServiceProxy serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    /**
     * @return the serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode the serviceCode to set
     */
    private void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * @return the params
     */
    public Object[] getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    private void setParams(Object[] params) {
        this.params = params;
    }

    /**
     * @return the listener
     */
    public IAsyncCallbackListener getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    private void setListener(IAsyncCallbackListener listener) {
        this.listener = listener;
    }

    /**
     * @return the extraData
     */
    public Object getExtraData() {
        return extraData;
    }

    /**
     * @param extraData the extraData to set
     */
    private void setExtraData(Object extraData) {
        this.extraData = extraData;
    }

    /**
     * @return the method
     */
    private Method getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    private void setMethod(Method method) {
        this.method = method;
    }       

    /**
     * @return the response
     */
    public Object getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(Object response) {
        this.response = response;
    }
    
}
