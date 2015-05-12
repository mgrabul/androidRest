package com.androidrest.restclient;

import android.app.FragmentManager;

import com.androidrest.restclient.events.InterfaceActivityThrowError;
import com.androidrest.restclient.events.OnFailure;

/*
   add in RestTask.onFailure()
 */
public class ErrorManager implements OnFailure {
	private InterfaceActivityThrowError activity;
	private FragmentManager fragmentManager;


	public ErrorManager(InterfaceActivityThrowError cont) {
		this.activity = cont;
	}

	public void setFragmentManager(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	@Override
	public void onFinish(RestConnection sc, Exception e, String exceptionError, String response) {
		int statusCode = sc.getStatusCode();
		// handle error if status then ...
	}


}
