package com.androidrest.restclient.events;


public interface OnSuccess<GenericData> {
	public void success(GenericData genericData, String response);

}
