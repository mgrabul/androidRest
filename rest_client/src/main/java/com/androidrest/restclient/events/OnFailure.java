package com.androidrest.restclient.events;


import com.androidrest.restclient.RestConnection;

public interface OnFailure {
	public void onFinish(RestConnection sc, Exception e, String exceptionError, String response);
}
