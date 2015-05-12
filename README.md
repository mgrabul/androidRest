# androidRest
Library for HTTP connection
features:
Make HTTP GET, POST, PUT, DELETE requests, Sends form requests, and payload requests, supports gzip, set connection timeout, 
abord connection, set headers,get status code, option to get a ResponseStringBuffer or a InputStream from the connection
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
Simple post example: </br>

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
