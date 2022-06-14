package com.example.myapplication.domain.rest;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.domain.Event;
import com.example.myapplication.domain.Mapper.EventMapper;
import com.example.myapplication.NoDb;
import com.example.myapplication.domain.Mapper.UserMapper;
import com.example.myapplication.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LibApiVolley implements LibApi {
    public static final String API_TEST = "API_TEST";
    private final Context context;
    public static final String BASE_URL = "http://192.168.1.64:8090";
    private Response.ErrorListener errorListener;

    public LibApiVolley(Context context) {
        this.context = context;
        errorListener = error -> { error.printStackTrace();};
    }

    @Override
    public void fillEvent() {
        String url = BASE_URL + "/event";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    NoDb.EVENT_LIST.clear();
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject jsonObject = response.getJSONObject(i);
                        Event event = EventMapper.eventFromJson(jsonObject);
                        NoDb.EVENT_LIST.add(event);
                    }
                    Log.d(API_TEST, NoDb.EVENT_LIST.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, errorListener);
        requestQueue.add(arrayRequest);

    }

    @Override
    public void fillUser() {
        String url = BASE_URL + "/user";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        User user = UserMapper.eventFromJson(jsonObject);
                        NoDb.USER_LIST.add(user);
                    }
                    Log.d(API_TEST, NoDb.USER_LIST.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, errorListener);
        requestQueue.add(arrayRequest);

    }

    @Override
    public void fillUserByEmail(String mail) {

        String url = BASE_URL + "/user/" + mail;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    User user = UserMapper.eventFromJson(jsonObject);
                    NoDb.user = user;
                    Log.d(API_TEST, NoDb.user.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, errorListener);
        requestQueue.add(arrayRequest);
    }


    @Override
    public void addUser(User user) {
        String url = BASE_URL + "/user";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fillUser();
                Log.d(API_TEST, response);
            }
        }, errorListener
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("password", user.getPassword());
                params.put("mail", user.getE_mail());

                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public void addEvent(Event event) {
        String url = BASE_URL + "/event";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fillEvent();
                Log.d(API_TEST, response);
            }
        }, errorListener
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", String.valueOf(event.getUser_id()));
                params.put("event", event.getEvent());
                params.put("time", event.getTime());

                return params;
            }
        };
        requestQueue.add(request);


    }

    @Override
    public void updateUser(int id, String newPassword, String newMail) {


    }
}
