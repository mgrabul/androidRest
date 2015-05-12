package com.androidrest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidrest.restclient.RestConnection;
import com.androidrest.restclient.RestTask;
import com.androidrest.restclient.events.OnFailure;
import com.androidrest.restclient.events.OnFinishTask2;
import com.androidrest.restclient.events.OnSuccess;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void exampleGet() {
        RestTask<String> rt = new RestTask<String>(this) {
            @Override
            public void executeInBackGround(Context context) throws IllegalArgumentException, IOException, URISyntaxException, JSONException {
                //set the rest connection
                this.sc = MainActivity.this.initConnection();
                this.sc.setExtend("");
                //set make request
                this.sc.connectGet();
                //set response
                this.response = this.sc.getResponseStringBuffer().toString();
                //here generic response
                this.genericData = response;
                Log.i("testResponse","get "+  response);
            }
        };
        rt.setOnSuccess(new OnSuccess() {
            @Override
            public void success(Object o, String response) {
                //success happens on http status 200 and no exception in executeInBackground
            }
        });
        rt.setOnFailure(new OnFailure() {
            @Override
            public void onFinish(RestConnection sc, Exception e, String exceptionError, String response) {

            }
        });
        rt.setOnFinishTask(new OnFinishTask2() {
            @Override
            public void onFinish(RestConnection sc, Exception e, String exceptionError, Object o, String response) {

            }
        });
        rt.execute();
    }

    void examplePostForm() {
        RestTask<String> rt = new RestTask<String>(this) {
            @Override
            public void executeInBackGround(Context context) throws IllegalArgumentException, IOException, URISyntaxException, JSONException {
                //set the rest connection
                this.sc = MainActivity.this.initConnection();
                this.sc.setExtend("");
                //set make request
                this.sc.addUrlParameters("param","value");
                this.sc.connectPost("");

                //set response
                this.response = this.sc.getResponseStringBuffer().toString();
                //here generic response
                this.genericData = response;
                Log.i("testResponse","get "+  response);
            }
        };
        rt.setOnSuccess(new OnSuccess() {
            @Override
            public void success(Object o, String response) {
                //success happens on http status 200 and no exception in executeInBackground
            }
        });
        rt.setOnFailure(new OnFailure() {
            @Override
            public void onFinish(RestConnection sc, Exception e, String exceptionError, String response) {

            }
        });
        rt.setOnFinishTask(new OnFinishTask2() {
            @Override
            public void onFinish(RestConnection sc, Exception e, String exceptionError, Object o, String response) {

            }
        });
        rt.execute();
    }

    void examplePostPayload() {
        RestTask<String> rt = new RestTask<String>(this) {
            @Override
            public void executeInBackGround(Context context) throws IllegalArgumentException, IOException, URISyntaxException, JSONException {
                //set the rest connection
                this.sc = MainActivity.this.initConnection();
                this.sc.setExtend("");
                //set make request
                this.sc.connectPost("{\"param\":\"value\"}");
                //set response
                this.response = this.sc.getResponseStringBuffer().toString();
                //here generic response
                this.genericData = response;
                Log.i("testResponse","post "+  response);
            }
        };
        rt.setOnSuccess(new OnSuccess() {
            @Override
            public void success(Object o, String response) {
                //success happens on http status 200 and no exception in executeInBackground
            }
        });
        rt.setOnFailure(new OnFailure() {
            @Override
            public void onFinish(RestConnection sc, Exception e, String exceptionError, String response) {

            }
        });
        rt.setOnFinishTask(new OnFinishTask2() {
            @Override
            public void onFinish(RestConnection sc, Exception e, String exceptionError, Object o, String response) {

            }
        });
        rt.execute();
    }

    void exampleDelete() {
        RestTask<String> rt = new RestTask<String>(this) {
            @Override
            public void executeInBackGround(Context context) throws IllegalArgumentException, IOException, URISyntaxException, JSONException {
                //set the rest connection
                this.sc = MainActivity.this.initConnection();
                this.sc.setExtend("");
                //set make request
                this.sc.connectDelete("");
                //set response
                this.response = this.sc.getResponseStringBuffer().toString();
                //here generic response
                this.genericData = response;
            }
        };
        rt.setOnSuccess(new OnSuccess() {
            @Override
            public void success(Object o, String response) {
                //success happens on http status 200 and no exception in executeInBackground
                Log.i("testResponse", "post " + response);
            }
        });

        rt.execute();
    }

    void examplePut() {

    }

    static RestConnection initConnection() {
        // http://stackoverflow.com/questions/5669045/android-generic-user-agent-ua
        RestConnection sc = new RestConnection();
        sc.setBaseUrl("http://ip.jsontest.com");
        sc.addHeader("platform", "android");
        // support for gzip
        sc.addHeader("Accept-Encoding", "gzip");
        sc.addHeader("Content-Type", "application/json");
        return sc;
    }
}
