package com.aasshh.user.services;

import android.content.Context;
import android.util.Log;

import com.aasshh.user.utils.SessionHandler;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RequestApi {
    Context context;
    String token;
    SessionHandler sessionHandler;
    String TAG = RequestApi.class.getSimpleName();
    RequestQueue queue;


    public RequestApi(Context context) {
        this.context = context;
        sessionHandler = new SessionHandler(this.context);
        token = sessionHandler.getLoggedToken();
        Log.d(TAG, "RequestApi:Token  " + token);
        queue = Volley.newRequestQueue(this.context);
    }

    public void postRequest(String url, JSONObject postData, Response.Listener<JSONObject> success) {
        JsonObjectRequest request = new JsonObjectRequest(url, postData, success, error -> Log.d(TAG, "onErrorResponse: " + error.getMessage()));
        queue.add(request);
    }

}
