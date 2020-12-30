package com.aasshh.user.services;

import android.content.Context;
import android.util.Log;

import com.aasshh.user.utils.SessionHandler;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestApi {
    Context context;
    String token;
    SessionHandler sessionHandler;
    String TAG = RequestApi.class.getSimpleName();
    RequestQueue queue;


    public RequestApi(Context context) {
        this.context = context;
        sessionHandler = new SessionHandler(this.context);
        token ="Bearer " + sessionHandler.getLoggedToken();
        Log.d(TAG, "RequestApi:Token  " + token);
        queue = Volley.newRequestQueue(this.context);
    }

    public void postRequest(String url, JSONObject postData, Response.Listener<JSONObject> success) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url, postData, success, error -> Log.d(TAG, "onErrorResponse: " + error.getMessage())) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", token);
                return map;
            }
        };
        queue.add(request);
    }

    /**
     * Get Request
     *
     * @param url
     * @param success
     */
    public void getRequest(String url, Response.Listener<String> success) {
//        Log.d(TAG, "getRequest: " + sessionHandler.getLoggedToken());
        StringRequest request = new StringRequest(Request.Method.GET
                , url, success, error -> {
            Log.d(TAG, "getRequest: " + error.getMessage());
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> map = new HashMap<>();
                map.put("auth", token);
                return map;
            }
        };
        queue.add(request);
    }
}
