api-java-client-library
=======================

Library to execute the Akamon core api services, through the http rest interface, in java.

# Compilation

> ant -buildfile project-manager.xml

The above command generate two jars in the dist directory:

* akamon-api-client-base-version.jar
* akamon-api-client-proxy-version.jar

# Unit tests

> ant -f project-manager.xml test

# Usage

You need the two jars (akamon-api-client-base-version.jar and akamon-api-client-proxy-version.jar)
in your project.

The base jar provides you the core funcionaly, and the proxy jar the final classes you 
are going to use directly in your application.

The proxy library read it's configuration from some yaml files located in the
package com.akamon.api.client.service.definition. Please look at the existents
files when the addition of new services were needed.


Another important issue is about the authentication. With every Proxy object,
you have to provide the authentication data in an AuthData object.

```
AuthData auth = new AuthData("app_code", "app_token", "sha256");
```

The app_code, app_token and sign method should be commnunicated to you by the api team. 

Below you can find a complete code example :
   
```

TestsProxy p = new TestsProxy("http://api.akamon.com", new AuthData("app_code","app_token","sha256"));
        
SayHelloResponseData res = p.sayHello("Peter");
        
System.out.println("Greeting received: " + res.getGreeting());

```

Please replace "http://api.akamon.com" with the right protocol and server domain
for the environment you are using (development, integratio, etc).

# Async usage

In order to make async calls, you can use the AsyncInvocationClient class :

```

// Init the library (if you didn't do it so)
ServiceConfigManager.registerConfigDir("....."); // Path to you config service files 
AuthData auth = new AuthData("app_code", "app_token", "sha256"); // Replace by the auth data the akamon api team provided you

// Create the proxy object, to perform the call
TestsProxy proxy = new TestsProxy(auth);

// Create the listener, to be notified when the call finishes                        
IAsyncCallbackListener listener = new IAsyncCallbackListener (){

    @Override
    public void successAction(String serviceCode, Object response, Object[] requestParams, Object extraData) {
        SayHelloResponseData res = (SayHelloResponseData) response;
                    
        System.out.println("Received: " + res.getGreeting() + " extra data " + (extraData == null ? "null" : extraData.toString()));
    }

    @Override
    public void errorAction(String serviceCode, Exception ex, Object[] requestParams, Object extraData) {
      System.err.println("errorAction");
      ex.printStackTrace();
    }
                
};
            
Object[] params = {"Mike"}; // Parameters for the call. Notice it has to be coherent with the sayHello method of the proxy object
String extraData = "invocacion1"; // Optional extra data that will be passed to the listener

// Create the async client
AsyncInvocationClient asyncClient = new AsyncInvocationClient(proxy, "sayHello", params, listener, extraData);

// Start the thread. When it finishes, you'll be notified in your listener class
asyncClient.start();

```