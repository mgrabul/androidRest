# androidRest
Library for HTTP connection
<h2>features</h2>
<ul>
<li>GET</li> 
<li>POST</li> 
<li>PUT</li> 
<li>DELETE</li> 
<li>form requests</li> 
<li>payload requests</li> 
<li>supports gzip</li> 
<li>set connection timeout</li>
<li>kill connection,abort connection</li>
<li>set headers</li>
<li>get status code</li>
<li>return the body as ResponseStringBuffer or InputStream</li>
<li>Option to make server calls in a new theread</li>
<!---
It contains a RestTask class that is a task that extends from AsyncTask and is used to support the calls in a new thread.
The RestTask has interfaces for a onSuccess, onFailure and onFinish interfaces that are triggered depending on the output of the HTTP call.
-->
</ul>
</br></br>
Simple example GET example:</br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
//set extend on the url</br>
sc.setExtend("/test")</br>
sc.connectGet();</br>
// get the response</br>
String response = sc.getResponseStringBuffer().toString()</br>
</br></br>
Simple POST payload example with json body: </br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
//set extend on the url</br>
sc.setExtend("/test")</br>
sc.connectPost("{"jsonParam":"json value"}");</br>
// get the response</br>
String response  = sc.getResponseStringBuffer().toString();
<!--InputStream response = sc.getResponseStream()</br>-->
</br></br>
Simple Put example</br>
Simple example get example:</br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
//set extend on the url</br>
sc.setExtend("/test")</br>
sc.connectPut("{"jsonParam":"json value"}");</br>
// get the response</br>
String response = sc.getResponseStringBuffer().toString()</br></br>
Simple DELETE example: </br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
sc.addPayloadParameters("id", "23");
sc.connectDelete("");
<br></br>
//Form request</br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
sc.setExtend("/test");</br>
//Add form's key value elements</br>
sc.addPayloadParameters("id", "7");</br>
sc.addPayloadParameters("age", "20");</br>
// add empty string in the connectPost method to send a http form</br>
sc.connectPost("");</br>
//get the response</br>
String response  = sc.getResponseStringBuffer().toString();
</br></br>
//Json body request</br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
sc.setExtend("/test");</br>
// json string is send in the connection mettod</br>
sc.connectPost("{"jsonParam":"json value"}");</br>
//get the response</br>
String response  = sc.getResponseStringBuffer().toString();
</br></br>
//Abort connection</br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
sc.setExtend("/test");</br>
// json string is send in the connection mettod</br>
sc.connectPost("{"jsonParam":"json value"}");</br>
//abort connnection</br>
sc.abortConnection();
<br></br>
//set headers
</br>
RestConnection sc = new RestConnection();</br>
//add headers</br>
sc.addHeader("header_name","header_value");
<br></br>
//get status code
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
//set extend on the url</br>
sc.setExtend("/test")</br>
sc.connectGet();</br>
//get status code after calling one of the connect methods</br>
sc.getStatusCode();</br></br>

return the body as ResponseStringBuffer or InputStream</br>
RestConnection sc = new RestConnection();</br>
//sets base url</br>
sc.setBaseUrl("http://ip.jsontest.com");</br>
sc.connectGet();</br>
InputStream response  = sc.getResponseStream();</br>
//or</br>
StringBuffer response  = sc.getResponseStringBuffer();</br>

<h2>RestTask</h2>
It allows you to make a call in a new thread, This class is based/extens from the androids AsyncTask, but unlike the android  AsyncTask that is a general all purpuse thread, the RestTask it is made to handle HTTP connections. It has the folowing life cycle:
startProgress() method is executed in the threadd the started the new RestTask, and it is the first method that is owned byt he assyng task.
executeInBackGround(Context context) is the method that is executed in a new thread, the main method the class is designed to extend this method and perform the heavy and time consunimng operations here, that is retriving the data from a http/https conection,parse this data, save to db.
onSuccess.success(GenericData genericData, String response), it is an interface that is trigered when no error has happened in the executeInBackGround(no http error, no parsing error, no persisging error).
OnFailure.onFinish(RestConnection sc, Exception e, String exceptionError, String response), it is an interface when some error hapened in the executeInBackGround(Context context), the interfaces methode is very conviniend and provides you, the RestConnnection object, the Exception, the string response that was returned from the server.
----OnFinishTask2.onFinish(RestConnection sc, Exception e, String exceptionError,
						 GenericData genericData, String response),it is the last method in the lifecycle, returns executes in any case when the backround thread is over, this can be used if you want to handle boath methods OnFailure.onFinish and  onSuccess.success the same or want to know when this are over.




