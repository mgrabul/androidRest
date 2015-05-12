package com.androidrest.restclient;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.androidrest.restclient.events.InterfaceActivityThrowError;
import com.androidrest.restclient.events.OnFailure;
import com.androidrest.restclient.events.OnFinishTask2;
import com.androidrest.restclient.events.OnSuccess;
import com.google.gson.JsonSyntaxException;

public class RestTask<ResponseObject> extends AsyncTask<String, String, String> {
    protected Context context;
    protected Exception exception;
    protected RestConnection sc;
    protected OnFinishTask2<ResponseObject> onFinishTask;
    protected OnSuccess<ResponseObject> onSuccess;
    protected OnFailure onFailure;
    protected String exceptionError = null;
    protected ResponseObject genericData;
    protected String response;


    public RestTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        exception = null;
        sc = null;
        response = null;
        try {
            executeInBackGround(context);
        } catch (IllegalArgumentException e) {
            exception = e;
            e.printStackTrace();
            exceptionError = "IllegalArgumentException";
        }
        catch (ClientProtocolException e) {
            exception = e;
            e.printStackTrace();
            exceptionError = "Check your internet connection";
        } catch (IOException e) {
            exception = e;
            e.printStackTrace();
            exceptionError = "Check your internet connection";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            exceptionError = "Parse Exception";
            exception = e;
            e.printStackTrace();
        } catch (JSONException e) {
            exceptionError = "Parse Exception, JSONException";
            exception = e;
            e.printStackTrace();
        } catch (NullPointerException e) {
            exceptionError = "Null poiter exception";
            exception = e;
            e.printStackTrace();
        }
        return null;
    }

    public void executeInBackGround(Context context) throws IllegalArgumentException, ClientProtocolException, IOException, URISyntaxException, JSONException {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        startProgress();
    }

    public void endProgress() {
        if (context instanceof InterfaceActivityThrowError) {
//			((InterfaceActivityThrowError) context).getProgress().setVisibility(View.GONE);
            // manage ending
        }
    }

    @Override
    protected void onPostExecute(String result) {

        endProgress();

        if (onFinishTask != null)
            onFinishTask.onFinish(sc, exception, exceptionError, genericData, response);
        if (sc.getStatusCode() == 200 && exception == null)
            if (onSuccess != null)
                onSuccess.success(genericData, response);
            else {
                if (onFailure != null)
                    onFailure.onFinish(sc, exception, exceptionError, response);
            }
        else {
            if (onFailure != null)
                onFailure.onFinish(sc, exception, exceptionError, response);
        }
        super.onPostExecute(result);
    }

    public void startProgress() {
        if (context instanceof InterfaceActivityThrowError) {
            ((InterfaceActivityThrowError) context).getProgress().setVisibility(View.VISIBLE);
        }
    }

    public RestConnection getServerConnection() {
        return sc;
    }

    public void setOnFinishTask(OnFinishTask2<ResponseObject> onFinishTask) {
        this.onFinishTask = onFinishTask;
    }

    public void setOnFailure(OnFailure onFailure) {
        this.onFailure = onFailure;
    }

    public void setOnSuccess(OnSuccess<ResponseObject> onSuccess) {
        this.onSuccess = onSuccess;
    }

}
