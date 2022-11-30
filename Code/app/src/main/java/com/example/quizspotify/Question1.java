package com.example.quizspotify;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Question1 extends AppCompatActivity {
    private final String urlArtiste1 = "https://api.spotify.com/v1/artists/0eDvMgVFoNV3TpwtrVCoTj?si=c6jzRYGwSEWNWNyP3kXElA";
    private final String urlArtiste2 = "https://api.spotify.com/v1/artists/6UCQYrcJ6wab6gnQ89OJFh?si=HC6fuPcKQ4enudg0_oykzQ";
    private SharedPreferences sharedPreferences;
    private RequestQueueSingleton instance;
    private Vector<Artiste> vectorArtiste;
    private NetworkImageView imageArtiste1, imageArtiste2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        this.sharedPreferences = this.getSharedPreferences("SPOTIFY",0);
        this.instance = RequestQueueSingleton.getInstance(this);
        this.vectorArtiste = new Vector<>();
    }

    public JsonObjectRequest creer_requête(String url){
        return new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
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

    public void ajouter_requête(){
        // On ajoute le premier artiste
        instance.addToRequestQueue(creer_requête(urlArtiste1));
        instance.addToRequestQueue(creer_requête(urlArtiste2));
    }

}
