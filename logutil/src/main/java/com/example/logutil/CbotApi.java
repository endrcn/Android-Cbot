package com.example.logutil;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CbotApi extends Application {
    //private static final String TAG =  "SUPER_AWESOME_APP";

    //public static void d(String message){
        //Log.d(TAG,message);
    //}

    //private OnTaskCompleted listener;

    //public LogDebug(OnTaskCompleted listener){
      //  this.listener=listener;
    //}


    private Application applicationContext;

    private RequestQueue requestQueue;

    private static CbotApi mInstance;



    public void onCreate(String url,final String token, JSONObject params, final CbotCallBack callback) {

        //this.listener=listener;
        super.onCreate();
        mInstance = this;



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, params,
                new Response.Listener()
                {
                    @Override
                    public void onResponse(Object response) {
                        callback.success(response);
                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        callback.error(error);

                    }
                }){
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("CBot-Token", token);
                return headers;
            }
        };



        //queue.add(jsonObjReq);

        CbotApi.getInstance().addToRequestQueue(jsonObjReq, "postRequest");

        //return jsonObjReq;
    }

    public void setApplicationContext(Application applicationContext) {
        this.applicationContext =  applicationContext;
    }

    public static synchronized CbotApi getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this.applicationContext);
        return requestQueue;
    }

    public void addToRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public interface CbotCallBack{
        void success(Object message);
        void error(Object message);
    }





    //public interface OnTaskCompleted{
      //  void onTaskCompleted(String result);
    //}
    //public class Callback implements OnTaskCompleted{
      //  @Override
        //public void onTaskCompleted(String result) {
            //return result;
        //}
    //}
}
