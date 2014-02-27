package com.akamon.api.client.net;

import java.util.Random;

/**
 *
 * @author miguelgarcia
 */
public class RequestIdGenerator {
    
    private final static RequestIdGenerator generator = new RequestIdGenerator();
        
    private Long lastRequestId;
    
    public final static RequestIdGenerator createRequestIdGenerator(){
        return generator;
    }
    
    private RequestIdGenerator () {        
        lastRequestId = null;
    }
    
    public synchronized long nextRequestId()
    {           
        final boolean notYetInitialized = (lastRequestId == null);
        
        if (notYetInitialized){
            setNextRequestIdFromRandom();
        }
        else {
            setNextRequestIdFromLastValue();
        }
        
        return lastRequestId.longValue();
    }
    
    private void setNextRequestIdFromRandom(){
        Random random = new Random();
        this.lastRequestId = random.nextLong();
        final boolean isNegative = this.lastRequestId < 0;
        
        if (isNegative){
            this.lastRequestId = - this.lastRequestId;
        }
    }
    
    private void setNextRequestIdFromLastValue(){
        boolean haveIReachedTheMaxValue = (lastRequestId.longValue() == Long.MAX_VALUE );
        
        if (haveIReachedTheMaxValue){
            lastRequestId = 0l;
        }
        else {
            lastRequestId++;
        }
    }
}
