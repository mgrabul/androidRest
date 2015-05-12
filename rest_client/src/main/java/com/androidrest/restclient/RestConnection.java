package com.androidrest.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.accounts.NetworkErrorException;
import android.os.NetworkOnMainThreadException;

public class RestConnection {

	private class JustBookHttpClient extends DefaultHttpClient {

		public JustBookHttpClient(HttpParams httpParameters) {
			super(httpParameters);
		}

		@Override
		protected ClientConnectionManager createClientConnectionManager() {
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", new EasySSLSocketFactory(),
					443));
			return new SingleClientConnManager(getParams(), registry);
		}
	}

	String extend = "";
	String baseAddres;
	HttpClient httpclient;
	HttpPost httpPost;
	HttpGet httpGet;
	HttpDelete httpDelete;
	HttpPut httpPut;
	List<NameValuePair> namePayloadParams;
	List<NameValuePair> urlParams;
	List<NameValuePair> headers;
	HttpEntity resEntity;
	int timeoutConnection = 80000;
	int timeoutSocket = 85000;
	private long streamLength;
	private int statusCode;
	private HttpResponse response;

	public RestConnection(String url) {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		httpclient = new JustBookHttpClient(httpParameters);
		this.baseAddres = url;
	}

	public RestConnection() {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		httpclient = new JustBookHttpClient(httpParameters);
	}

	public void setBaseUrl(String url) {
		this.baseAddres = url;
	}

	public void appendPath(String path) {
		this.baseAddres = this.baseAddres + path;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public void clearExtend() {
		this.extend = "";
	}

	public List<NameValuePair> getPayloadParams() {
		return namePayloadParams;
	}

	public void setPayloadParams(List<NameValuePair> namePayloadParams) {
		this.namePayloadParams = namePayloadParams;
	}

	public void addPayloadParameters(String name, String value) {
		try {
			removePaylodParam(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (namePayloadParams == null)
			namePayloadParams = new ArrayList<NameValuePair>();
		namePayloadParams.add(new BasicNameValuePair(name, value));
	}

	private void removePaylodParam(String name) {
		int intToRemove = -1;
		for (int i = 0; i < namePayloadParams.size(); i++) {
			if (namePayloadParams.get(i).getName().compareTo(name) == 0) {
				intToRemove = i;
			}
		}
		if (intToRemove != -1)
			namePayloadParams.remove(intToRemove);
	}

	public List<NameValuePair> getHeader() {
		return headers;
	}

	public void setHeader(List<NameValuePair> headers) {
		this.headers = headers;
	}

	public void addHeader(String name, String value) {
		try {
			removeHeader(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (headers == null)
			headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair(name, value));
	}

	private void removeHeader(String name) {
		int intToRemove = -1;
		for (int i = 0; i < headers.size(); i++) {
			if (headers.get(i).getName().compareTo(name) == 0) {
				intToRemove = i;
			}
		}
		if (intToRemove != -1)
			headers.remove(intToRemove);
	}

	public List<NameValuePair> getUrlParams() {
		return urlParams;
	}

	public void setUrlParams(List<NameValuePair> urlParams) {
		this.urlParams = urlParams;
	}

	public void addUrlParameters(String name, String value) {
		try {
			removePaylodParam(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (urlParams == null)
			urlParams = new ArrayList<NameValuePair>();
		urlParams.add(new BasicNameValuePair(name, value));
	}

	public void removeUrlParam(String name) {
		int intToRemove = -1;
		for (int i = 0; i < urlParams.size(); i++) {
			if (urlParams.get(i).getName().compareTo(name) == 0) {
				intToRemove = i;
			}
		}
		if (intToRemove != -1)
			urlParams.remove(intToRemove);
	}

	private String getCompleteUrl() {
		String paramString = "";
		try {
			paramString = URLEncodedUtils.format(urlParams, "UTF-8");
			paramString = "?" + paramString;
		} catch (Exception e) {
			paramString = "";
		}
		String url = baseAddres + extend + paramString;
		return url;
	}

	public InputStream getResponseStream() throws ClientProtocolException,
			IOException {

		InputStream returnStream = resEntity.getContent();
		Header contentEncoding = response.getFirstHeader("Content-Encoding");
		if (contentEncoding != null
				&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
			returnStream = new GZIPInputStream(returnStream);
		}
		return returnStream;
	}

	long getResponseLength() {
		return resEntity.getContentLength();
	}

	/**
	 * Use to make a post Request use the JsonObject.toString() as input
	 * parameter
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void connectPost(String jsonObject) throws IllegalArgumentException,
			ClientProtocolException, IOException {
		httpPost = new HttpPost(getCompleteUrl());
		if (jsonObject.compareTo("") != 0)
			httpPost.setEntity(new ByteArrayEntity(jsonObject.getBytes("UTF8")));
		else
			try {

				httpPost.setEntity(new UrlEncodedFormEntity(
						this.namePayloadParams));
			} catch (Exception e) {
				e.printStackTrace();
			}

		if (headers != null)
			for (NameValuePair iterable_element : headers) {
				httpPost.setHeader(iterable_element.getName(),
						iterable_element.getValue());

			}
		int lenght = httpPost.getAllHeaders().length;

		response = httpclient.execute(httpPost);
		resEntity = response.getEntity();

		statusCode = response.getStatusLine().getStatusCode();
	}

	/**
	 * Use to make a post Request use the JsonObject.toString() as input
	 * parameter
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void connectPut(String jsonObject) throws IllegalArgumentException,
			ClientProtocolException, IOException {
		httpPut = new HttpPut(getCompleteUrl());
		if (jsonObject.compareTo("") != 0)
			httpPut.setEntity(new ByteArrayEntity(jsonObject.getBytes("UTF8")));
		else
			try {

				httpPut.setEntity(new UrlEncodedFormEntity(
						this.namePayloadParams));
			} catch (Exception e) {
				e.printStackTrace();
			}

		if (headers != null)
			for (NameValuePair iterable_element : headers) {
				httpPut.setHeader(iterable_element.getName(),
						iterable_element.getValue());
			}

		response = httpclient.execute(httpPut);
		resEntity = response.getEntity();
		statusCode = response.getStatusLine().getStatusCode();
	}

	/**
	 * Use to make a get request
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws HttpStatusException
	 */
	public void connectDelete(String jsonObject)
			throws IllegalArgumentException, ClientProtocolException,
			IOException, URISyntaxException {
		httpDelete = new HttpDelete(getCompleteUrl());
		if (headers != null)
			for (NameValuePair iterable_element : headers) {
				httpDelete.setHeader(iterable_element.getName(),
						iterable_element.getValue());
			}

		response = httpclient.execute(httpDelete);
		resEntity = response.getEntity();
		statusCode = response.getStatusLine().getStatusCode();
	}

	/**
	 * Use to make a get request
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws HttpStatusException
	 */

	public void connectGet() throws IllegalArgumentException,
			ClientProtocolException, IOException, URISyntaxException {
		String url = getCompleteUrl();
		httpGet = new HttpGet(url);
		if (headers != null) {
			this.baseAddres = url;
			for (NameValuePair iterable_element : headers) {
				httpGet.setHeader(iterable_element.getName(),
						iterable_element.getValue());
			}
		}
		int lenght = httpGet.getAllHeaders().length;

		response = httpclient.execute(httpGet);

		resEntity = response.getEntity();

		streamLength = resEntity.getContentLength();

		statusCode = response.getStatusLine().getStatusCode();

	}

	public StringBuffer getResponseStringBuffer() throws IOException {
		StringBuffer strignBuffer = new StringBuffer();
		try {
			InputStream is = getResponseStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				strignBuffer = strignBuffer.append(inputLine);
			}
			in.close();

			return strignBuffer;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			throw new IOException("Cannot read from input stream (aborted)");
		}
		return null;
	}

	public void abortConnection() throws NetworkErrorException,
			NetworkOnMainThreadException {
		if (httpPost != null) {
			httpPost.abort();
		} else if (httpGet != null) {
			httpGet.abort();
		}
	}

	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * use only for testing
	 * 
	 * @param statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

    public HttpResponse getResponse()
    {
        return response;
    }
}
