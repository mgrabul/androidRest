# androidRest
Library for HTTP connection
features:
Make HTTP GET, POST, PUT, DELETE requests, Sends form requests, and payload requests, supports gzip, set connection timeout, 
abord connection, set headers,get status code, option to get a ResponseStringBuffer or a InputStream from the connection.
It contains a RestTask class that is a task that extends from AsyncTask and is used to support the calls in a new thread.
The RestTask has interfaces for a onSuccess, onFailure and onFinish interfaces that are triggered depending on the output of the HTTP call.
</br></br>
Simple example get example:</br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
//set extend on the url</br>
sc.setExtend("/test")</br>
//set header</br>
sc.addHeader("platform", "android");</br>
// support for gzip</br>
sc.connectGet();</br>
// get the response</br>
String response = sc.getResponseStringBuffer().toString()</br>
</br></br>
Simple post payload example: </br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
//set extend on the url</br>
sc.setExtend("/test")</br>
//set header</br>
sc.addHeader("platform", "android");</br>
// support for gzip</br>
sc.connectPost("{"jsonParam":"json value"}");</br>
// get the response</br>
InputStream response = sc.getResponseStream()</br>

<a href="secretescapes://open/"> test open </a>
<a href="secretescapes://open/1234"> test open </a>
