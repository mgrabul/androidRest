package com.androidrest.restclient.events;


import com.androidrest.restclient.RestConnection;

public interface OnFinishTask2<GenericData> {
	public void onFinish(RestConnection sc, Exception e, String exceptionError,
						 GenericData genericData, String response);

}
