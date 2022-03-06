package com.example.parsingtask2.networking;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.parsingtask2.MyApplication;
import com.example.parsingtask2.model.Post;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class VolleyHttp {

    public static boolean IS_TESTER = true;
    public static String SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/";
    public static String SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/";

    public static String server(String url) {
        if (IS_TESTER)
            return SERVER_DEVELOPMENT + url;
        return SERVER_PRODUCTION + url;
    }

    public static HashMap<String, String> headers() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json; charset=UTF-8");
        return headers;
    }

    public static void get(String api, HashMap<String, String> params, VolleyHandler volleyHandler) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server(api),
                (Response.Listener<String>) response -> {
                    volleyHandler.onSuccess(response);
                }, error -> {
            volleyHandler.onError(error.toString());
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void post(String api, HashMap<String, Object> body, VolleyHandler volleyHandler) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server(api),
                (Response.Listener<String>) response -> {
                    volleyHandler.onSuccess(response);
                }, error -> {
            volleyHandler.onError(error.toString());
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return new JSONObject(body).toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers();
            }
        };

        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void put(String api, HashMap<String, Object> body, VolleyHandler volleyHandler) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, server(api),
                (Response.Listener<String>) response -> {
                    volleyHandler.onSuccess(response);
                }, error -> {
            volleyHandler.onError(error.toString());
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return new JSONObject(body).toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers();
            }
        };

        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void deletePost(String url, VolleyHandler volleyHandler) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, server(url),
                response -> {
                    volleyHandler.onSuccess(response);
                }, error -> {
            volleyHandler.onError(error.toString());
        });

        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static String API_LIST_POST = "posts";
    public static String API_SINGLE_POST = "posts/"; //id
    public static String API_CREATE_POST = "posts";
    public static String API_UPDATE_POST = "posts/"; //id
    public static String API_DELETE_POST = "posts/"; //id

    public static HashMap<String, String> paramsEmpty() {
        return new HashMap<>();
    }

    public static HashMap<String, Object> paramsCreate(Post post) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("title", post.title);
        params.put("body", post.body);
        params.put("userId", post.userId);
        return params;
    }

    public static HashMap<String, Object> paramsUpdate(Post post) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", post.id);
        params.put("title", post.title);
        params.put("body", post.body);
        params.put("userId", post.userId);
        return params;
    }
}
