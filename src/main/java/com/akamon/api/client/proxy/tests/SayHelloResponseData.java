package com.akamon.api.client.proxy.tests;

/**
 * Bean to encapsulate the response obtained from server to the operation sayHello
 * @author Miguel Angel Garcia
 */
public class SayHelloResponseData {
    private String greeting;

    /**
     * @return the greeting
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * @param greeting the greeting to set
     */
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
    
}
