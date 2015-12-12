# androidRest
Library for HTTP connection
<h2>features</h2>
<ul>
<li>HTTP GET</li> 
<li>POST</li> 
<li>PUT</li> 
<li>DELETE</li> 
<li>form requests</li> 
<li>payload requests</li> 
<li>supports gzip</li> 
<li>set connection timeout</li>
<li>kill connection,abord connection</li>
<li>set headers</li>
<li>get status code</li>
<li>return the body as ResponseStringBuffer or InputStream</li>
<li>set headers</li>
<li>get status code,</li>
<li>Option to make server calls in a new theread</li>
<!---
It contains a RestTask class that is a task that extends from AsyncTask and is used to support the calls in a new thread.
The RestTask has interfaces for a onSuccess, onFailure and onFinish interfaces that are triggered depending on the output of the HTTP call.
-->
</ul>
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


