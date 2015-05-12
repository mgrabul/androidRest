package com.androidrest.restclient;

public class HttpStatusException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3516298056399987496L;
	int httpStatus;
	String requestContents;

	public HttpStatusException(String msg, int httpStatus) {
		super(msg);
		this.httpStatus = httpStatus;
	}

	public HttpStatusException(String msg, int httpStatus,
			String responseContents) {
		super(msg);
		this.httpStatus = httpStatus;
		this.requestContents = responseContents;
	}

	public HttpStatusException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * get the contents of the HttpResponse
	 * 
	 * @return
	 */
	public String getResponseContents() {
		return requestContents;
	}

	/**
	 * set the contents of the HttpResponse Set the contents on exception throw
	 * 
	 * @param requestContents
	 */
	public void setRequestContents(String requestContents) {
		this.requestContents = requestContents;
	}

	/**
	 * Returns the error code from the justBook Server use this value to call a
	 * error dialog
	 * 
	 * @return a dialog number with the appropriate exception
	 */
	public int getHttpStatus() {
		return httpStatus;
	}
}
