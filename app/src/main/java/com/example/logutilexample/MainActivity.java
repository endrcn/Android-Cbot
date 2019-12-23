package com.example.logutilexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.logutil.CbotApi;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.logutil.CbotApi.CbotCallBack;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //LogDebug logDebug = new LogDebug();

        CbotApi cbotApi = new CbotApi();



        CbotCallBack callback = new CbotCallBack() {
            @Override
            public void success(Object response) {
                Log.d("RESP", response.toString());
            }
            @Override
            public void error(Object response) {
                Log.d("RESP", response.toString());
            }

        };

        //new LogDebug(new LogDebug.Callback())

        cbotApi.setApplicationContext(getApplication());

        JSONObject postparams = new JSONObject();
        try {
            postparams.put("userId", "2123123");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            postparams.put("message", "merhaba");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cbotApi.onCreate("http://52.23.219.100:5351/anadoluhayat","82XBlXBAtXXdCd15u6TcGMWwY5LUE0IC",postparams,callback);

    }
}
