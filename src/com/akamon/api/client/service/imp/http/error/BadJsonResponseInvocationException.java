/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akamon.api.client.service.imp.http.error;

import com.akamon.api.client.error.BadResponseInvocationException;

/**
 *
 * @author Miguel Angel Garcia
 */
public class BadJsonResponseInvocationException extends BadResponseInvocationException {
     
    public BadJsonResponseInvocationException(String serviceCode, String jsonResponse){
        super(serviceCode, jsonResponse);
    }     
}
