package com.akamon.api.client.net;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import com.akamon.api.client.net.error.HttpRequestException;
import com.akamon.api.client.net.error.InvalidHttpMethodException;
import com.akamon.api.client.net.error.InvalidHttpUrlException;

/**
 * Class responsible for communicating (at low level) with the api rest server
 */
public class HttpAsyncClient {    
    private static final String SIGNATURE_CHARSET = "UTF-8";
    
   private String appCode;
   private String appToken;
   private java.util.logging.Logger logger;
   
   private final static HashMap<String, String> httpMethods;
   
   private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
   
   private static CloseableHttpAsyncClient httpclientSingleton = null;
   
   static {
       httpMethods = new HashMap();
       httpMethods.put("GET", "GET");
       httpMethods.put("POST", "POST");
       httpMethods.put("PUT", "PUT");
       httpMethods.put("DELETE", "DELETE");
   }     
    
   public HttpAsyncClient(String appCode, String appToken, java.util.logging.Logger logger){
       this.appCode = appCode;
       this.appToken = appToken;   
       this.logger = logger;
   }
   
   public HttpAsyncClient(String appCode, String appToken) {
       this(appCode, appToken, null);
   }
     
   public void execute(final String url, String method, final NameValuePair[] parameters, final FutureCallback<HttpResponseData> cb) throws HttpRequestException  {      
       
       if (!isValidHttpMethod(method)) {
           throw new InvalidHttpMethodException(method);
       }
       
       if (!isValidUrl(url)){
           throw new InvalidHttpUrlException(url);
       }      
       
          
       final String methodName = method.toString();                     
       
       final long requestId = RequestIdGenerator.createRequestIdGenerator().nextRequestId();                
       long startTime = System.currentTimeMillis();              
       Date requestInitDate = new Date();
       
       final String logPreffix = buildRequestLogIdentifier(requestId);
       
       StringBuilder logTextBuilder = new StringBuilder(logPreffix);
       logTextBuilder.append(" request start");       
       log(logTextBuilder.toString());     
       
       CloseableHttpAsyncClient httpClientPtr = null;
       
       try {                  
            method = method.toUpperCase();                           
            
            final int ts = getUtcTimestamp(new Date());
            final String signature = calculateSignature(parameters, ts);

            final HttpRequestBase request = createHttpRequest(methodName, url, parameters);
            addHeadersToRequest(request, appCode, requestId, ts, signature);                        

            final CloseableHttpAsyncClient httpClient = createHttpClient();
            httpClientPtr = httpClient;
                   
            final FutureCallback<HttpResponse> future = new FutureCallback<HttpResponse>() {

                public void completed(final HttpResponse response) {
                    int status_code = response.getStatusLine().getStatusCode();                         

                    HttpEntity entity = response.getEntity();
                    InputStream is;
                    String body;
					try {
						is = entity.getContent();
						body = IOUtils.toString(is, SIGNATURE_CHARSET);
					} catch (Exception e) {
						logRequestInfoOnError(logPreffix, e, url, methodName, parameters);               
				        return;
						//throw new HttpRequestException("Error executing an http request: (" + methodName + ") : " + url, e);  
					}
                    log(logPreffix + 
                            "requestId: " + requestId + "\n" + 
                            "firma: " + signature + "\n" + 
                            "parameters: " + requestParametersToString(parameters) + "\n" +                    
                            "timestamp: " + ts + "\n" +  
                            body);
                    
                    cb.completed(new HttpResponseData(status_code, body));
                }

                public void failed(final Exception ex) {
                    cb.failed(ex);
                }

                public void cancelled() {
                    cb.cancelled();
                }
            };
            
            
            httpClient.execute(request, future);
            return;
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
   
    
	private CloseableHttpAsyncClient createHttpClient() throws Exception
	{
		if(httpclientSingleton==null) 
		{
			httpclientSingleton = HttpAsyncClients.custom().setMaxConnTotal(100).setMaxConnPerRoute(100).build();
			
			httpclientSingleton.start();
		}
		
		return httpclientSingleton; 
	}
	   
   private void logRequestInfoOnError(String logPreffix, Exception e, String url, String method, NameValuePair[] parameters){
       
       StringBuilder logTextBuilder = new StringBuilder();
              
       logTextBuilder.append(logPreffix).append("Error in the ").append(method).append(" invokation : ").append(e.getClass().getName()).append(" - ").append(e.getMessage());
       log(logTextBuilder.toString(), Level.SEVERE);                     
                      
       logTextBuilder.delete(0, logTextBuilder.length());
       logTextBuilder.append(logPreffix).append(" url ").append(url);
       log(logTextBuilder.toString(), Level.SEVERE);
           
       logTextBuilder.delete(0, logTextBuilder.length());
       logTextBuilder.append(logPreffix).append(" parameters ").append(requestParametersToString(parameters));
       log(logTextBuilder.toString(), Level.SEVERE);                      
   }   
   
   private String buildRequestLogIdentifier(long requestId)
   {
              
       Thread currentThread = Thread.currentThread();
       String threadName = Thread.currentThread().getName();                        
       String threadPoolName = currentThread.getThreadGroup().getName();     
       
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
      
   private boolean isValidHttpMethod(String method){
       return ((method != null) &&  (httpMethods.containsKey(method.toUpperCase())));
   }
     
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
         
   private HttpRequestBase createHttpRequest(String methodName, String url, NameValuePair[] parameters) throws UnsupportedEncodingException{              
       HttpRequestBase request = null;       
       
       if(methodName.equals("GET")){
    	   request = new HttpGet(url);
       }
       else if (methodName.equals("POST")){
    	   request = new HttpPost(url);
           addParametersToRequest(request, parameters);
       }
       else if(methodName.equals("PUT")){
    	   request = new HttpPut(url);
           addParametersToRequest(request, parameters);
       }
       else if(methodName.equals("DELETE")){
    	   request = new HttpDelete(url);           
       }              
       
       return request;              
   }
   
   private void addParametersToRequest(HttpRequestBase request, NameValuePair[] parameters) throws UnsupportedEncodingException{
       
       List<NameValuePair> parametersList = new ArrayList<NameValuePair>(Arrays.asList(parameters));
       UrlEncodedFormEntity encodedUrl = new UrlEncodedFormEntity(parametersList, SIGNATURE_CHARSET);
       
       HttpEntityEnclosingRequestBase enclosingRequest = (HttpEntityEnclosingRequestBase) request;
       enclosingRequest.setEntity(encodedUrl);
   } 
   
   private void addHeadersToRequest(HttpRequestBase request, String appCode, long requestId, int ts, String signature) {
       request.addHeader("Accept", "application/json");
       request.addHeader("X-Akamon-AppCode", appCode);
       request.addHeader("X-Akamon-RequestId", Long.toString(requestId));
       request.addHeader("X-Akamon-Ts", "" + ts);
       request.addHeader("X-Akamon-Signature", signature);
   }               
      
   private String calculateSignature(NameValuePair[] params, int ts) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
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
        
        String query = queryStringBuilder.encode(SIGNATURE_CHARSET);
        
        if (query == null ){
            query = "";
        }
        else if ( (query.length() > 0) && (query.charAt(0) == '?') )
        {
            query = query.substring(1);
        }  
        
        StringBuilder dataToBeSigned = new StringBuilder(query.toLowerCase());
        dataToBeSigned.append(ts);                
        
        Signer signer = new Signer(appToken);
        
        return signer.generateSignature(dataToBeSigned.toString());                              
    }            
     
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
   
   private void log(String message, Level level){
        if ( this.logger != null ){
            Date now = new Date();
            StringBuilder messageBuilder = new StringBuilder();            
            
            messageBuilder.append(dateFormat.format(now));
            messageBuilder.append(" ");
            messageBuilder.append(message);
            
            this.logger.log(level, messageBuilder.toString());
        } 
   }
   
   private void log(String message){
        log(message, Level.CONFIG);
   }   
}
