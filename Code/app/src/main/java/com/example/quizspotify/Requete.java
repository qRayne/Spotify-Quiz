package com.example.quizspotify;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Requete {
    private SharedPreferences sharedPreferences;
    private RequestQueueSingleton instance;
    private Context context;

    public Requete(Context context) {
        this.context = context;
        this.sharedPreferences = context.getApplicationContext().getSharedPreferences("SPOTIFY",0);
        this.instance = RequestQueueSingleton.getInstance(context);
    }

    public StringRequest creer_requête(String url){
        return new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new GsonBuilder().create();

                        if (context instanceof Question1) {
                            System.out.println(response);
                            Artiste artiste = gson.fromJson(response,Artiste.class);
                            ((Question1)context).afficherInfos(artiste);
                        }
                    }
                },null){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
    }

    public void ajouter_requête(String url1,String url2){
        // On ajoute le premier artiste et le second artiste
        instance.addToRequestQueue(creer_requête(url1));
        instance.addToRequestQueue(creer_requête(url2));
    }


    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public RequestQueueSingleton getInstance() {
        return instance;
    }

    public void setInstance(RequestQueueSingleton instance) {
        this.instance = instance;
    }
}
