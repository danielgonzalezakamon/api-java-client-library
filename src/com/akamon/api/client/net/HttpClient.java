package com.akamon.api.client.net;

import com.akamon.api.client.net.error.HttpRequestException;
import com.akamon.api.client.net.error.InvalidHttpMethodException;
import com.akamon.api.client.net.error.InvalidHttpUrlException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.logging.Level;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;

/**
 * Class responsible for communicating (at low level) with the api rest server
 * @author Miguel Angel Garcia
 */
public class HttpClient {
   private String appCode;
   private String appToken;
   private java.util.logging.Logger logger;
   
   private final static HashMap<String, String> httpMethods;
   
   private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
   
   static {
       httpMethods = new HashMap();
       httpMethods.put("GET", "GET");
       httpMethods.put("POST", "POST");
       httpMethods.put("PUT", "PUT");
       httpMethods.put("DELETE", "DELETE");
   }     
   
   /**
    * Builds the object
    * @param appCode Public identifier
    * @param appToken Secret token
    */
   public HttpClient(String appCode, String appToken, java.util.logging.Logger logger){
       this.appCode = appCode;
       this.appToken = appToken;   
       this.logger = logger;
   }
   
   public HttpClient(String appCode, String appToken) {
       this(appCode, appToken, null);
   }
   
   /**
    * Performs and http call
    * @param url Service url
    * @param method Http method (POST, GET, PUT, DELETE)
    * @param parameters Key-valued parameters for the request
    * @return Response from server
    * @throws HttpRequestException 
    */ 
   public HttpResponseData execute(String url, String method, NameValuePair[] parameters) throws HttpRequestException {
       
       if (!isValidHttpMethod(method)) {
           throw new InvalidHttpMethodException(method);
       }
       
       if (!isValidUrl(url)){
           throw new InvalidHttpUrlException(url);
       }             
              
       Thread currentThread = Thread.currentThread();
       String threadName = Thread.currentThread().getName();                        
       String threadPoolName = currentThread.getThreadGroup().getName();     
       
       long requestId = RequestIdGenerator.createRequestIdGenerator().nextRequestId();         
       
       long startTime = System.currentTimeMillis();       
       
       Date requestInitDate = new Date();
       
       String logPreffix = buildRequestLogIdentifier(requestId, threadName, threadPoolName);
       
       StringBuilder logTextBuilder = new StringBuilder(logPreffix);
       logTextBuilder.append(" request start");
       
       log(logTextBuilder.toString());       
       
       try {                  
            method = method.toUpperCase();                           
            
            int ts = getUtcTimestamp(new Date());
            String firma = calculateSign(parameters, ts);

            Request req = createHttpRequest(method, url);
            req.addHeader("Accept", "application/json");
            req.addHeader("X-Akamon-AppCode", appCode);
            req.addHeader("X-Akamon-RequestId", Long.toString(requestId));
            req.addHeader("X-Akamon-Ts", "" + ts);
            req.addHeader("X-Akamon-Signature", firma);

            if ( (method.equals("POST")) || (method.equals("PUT")) ){
                 req.bodyForm(parameters);
            }

            HttpResponse httpRes = req.execute().returnResponse();
            int status_code = httpRes.getStatusLine().getStatusCode();      

            HttpEntity entity = httpRes.getEntity();
            InputStream is = entity.getContent();
            String body = IOUtils.toString(is, "UTF-8");
            
            return new HttpResponseData(status_code, body);
       }       
       catch (Exception e){
           logRequestInfoOnError(logPreffix, e, url, method, parameters);               
           
           throw new HttpRequestException("Error executing an http request: (" + method + ") : " + url, e);
       } 
       finally {    
           long elapsedTime = System.currentTimeMillis() - startTime;  
           
           logTextBuilder.delete(0, logTextBuilder.length());
           logTextBuilder.append(logPreffix).append(" end request started at ").append(dateFormat.format(requestInitDate)).append(" elapsed milliseconds ").append(elapsedTime);
           log(logTextBuilder.toString());           
       }
   }
   
   private void logRequestInfoOnError(String logPreffix, Exception e, String url, String method, NameValuePair[] parameters){
       
       StringBuilder logTextBuilder = new StringBuilder();
              
       logTextBuilder.append(logPreffix).append("Error in the ").append(method).append(" invokation : ").append(e.getClass().getName()).append(" - ").append(e.getMessage());
       log(logTextBuilder.toString());                     
                      
       logTextBuilder.delete(0, logTextBuilder.length());
       logTextBuilder.append(logPreffix).append(" url ").append(url);
       log(logTextBuilder.toString());
           
       logTextBuilder.delete(0, logTextBuilder.length());
       logTextBuilder.append(logPreffix).append(" parameters ").append(requestParametersToString(parameters));
       log(logTextBuilder.toString());                      
   }   
   
   private String buildRequestLogIdentifier(long requestId, String threadName, String threadPoolName)
   {
       StringBuilder builder = new StringBuilder();
       builder.append("reqId: ").append(requestId).append(" ").append(threadName).append(".").append(threadPoolName).append(" ");      
       
       return builder.toString();
   }
   
   private String requestParametersToString(NameValuePair[] parameters){
       StringBuilder stringified = new StringBuilder();
       
       for (NameValuePair param : parameters){           
           stringified.append(param.getName());
           stringified.append("=");
           stringified.append(param.getValue());
           stringified.append("&");
       }
       
       return stringified.toString();
   }
   
   /**
    * Returns true if the http method is valid
    * @param method (POST, GET, PUT, DELETE)
    * @return true if the method is valid
    */
   private boolean isValidHttpMethod(String method){
       return ((method != null) &&  (httpMethods.containsKey(method.toUpperCase())));
   }
   
   /**
    * Returns true if the url method is valid
    * @param url The url to validate
    * @return true if the url is valid
    */
   private boolean isValidUrl(String url){
       url = url == null ? "" : url.toLowerCase();
       
       if (url.equals("")) return false;
       
       if ( (!url.startsWith("http://")) && (!url.startsWith("https://")) ){
           return false;
       }
       
       try {
           new URL(url);
       }
       catch (Exception e){
           return false;
       }
       
       return true;
   }
   
   /**
    * Creates a request object
    * @param method Http method 
    * @param url Url to call
    * @return Request obtained from the server
    */
   private Request createHttpRequest(String method, String url){
       Request req = null;
       method = method.toUpperCase();
       if(method.equals("GET"))
    	   req = Request.Get(url);
       else if(method.equals("POST"))
    	   req = Request.Post(url);
       else if(method.equals("PUT"))
    	   req = Request.Put(url);
       else if(method.equals("DELETE"))
    	   req = Request.Delete(url);
      
       return req;
   }
   
   /**
    * Calculates the right sign for the operation
    * @param params Request parameters
    * @param ts Timestamp for the request
    * @return Sign String with the rigt signatuire to send with the request
    * @throws UnsupportedEncodingException
    * @throws NoSuchAlgorithmException
    * @throws InvalidKeyException 
    */
   private String calculateSign(NameValuePair[] params, int ts) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException{
        Arrays.sort(params, new Comparator(){
            @Override
            public int compare(Object t1, Object t2) {                                
                return ((NameValuePair) t1).getName().compareTo(((NameValuePair) t2).getName());                
            }                                    
        });
        
        QueryStringBuilder queryStringBuilder = new QueryStringBuilder();        
        
        for (NameValuePair p : params) {
            queryStringBuilder.addQueryParameter(p);
        }
        
        String query = queryStringBuilder.encode("UTF-8");
        
        if (query == null ){
            query = "";
        }
        else if ( (query.length() > 0) && (query.charAt(0) == '?') )
        {
            query = query.substring(1);
        }                              
                       
        return buildHmacSignature(appToken, query.toLowerCase() + ts);       
    }
   
   /**
    * Calculates the hashHmac signature
    * @param pKey Seed
    * @param pStringToSign Data to be signed
    * @return Signature
    * @throws InvalidKeyException 
    */
    private String buildHmacSignature(String pKey, String pStringToSign) throws InvalidKeyException {
      String lSignature = "";
      try
      {
        Mac lMac = Mac.getInstance("HmacSHA256");
        SecretKeySpec lSecret = new SecretKeySpec(pKey.getBytes(), "HmacSHA256");
        lMac.init(lSecret);

        byte[] lDigest = lMac.doFinal(pStringToSign.getBytes());
        BigInteger lHash = new BigInteger(1, lDigest);
        lSignature = lHash.toString(16);
        if ((lSignature.length() % 2) != 0) {
          lSignature = "0" + lSignature;
        }
      }
      catch (NoSuchAlgorithmException lEx)
      {
        throw new RuntimeException("Problems calculating HMAC", lEx);
      }
      catch (InvalidKeyException lEx)
      {
        throw new RuntimeException("Problems calculating HMAC", lEx);
      }

      return lSignature;
   }      
   
   /**
    * Gets the unix timestamp, int the GMT timezone, for the given date
    * @param date
    * @return Timestamp in UNIX format (seconds from 1970-01-01 to date)
    */
   private int getUtcTimestamp(Date date){
        Calendar c = Calendar.getInstance();
        
        c.setTime(date);

        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();
        if(z.inDaylightTime(date)){
            offset = offset + z.getDSTSavings();
        }
        int offsetHrs = offset / 1000 / 60 / 60;
        int offsetMins = offset / 1000 / 60 % 60;                
        
        return ((int) (date.getTime() / 1000L)) + (offsetHrs * 3600) + (offsetMins * 60);                        
    }
   
   private void log(String message){
        if ( this.logger != null ){
            Date now = new Date();
            this.logger.log(Level.INFO, dateFormat.format(now) + " " + message);
        } 
   }
   
}
