package com.androidrest.restclient.events;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

public interface InterfaceActivityThrowError {
	public View getRecoverableView();

	public View getNetworkErrorView();

	public Context getActivityContext();
	
	public ProgressBar getProgress();

}
