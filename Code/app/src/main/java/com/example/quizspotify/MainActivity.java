package com.example.quizspotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.spotify.com/v1/artists/0eDvMgVFoNV3TpwtrVCoTj?si=c6jzRYGwSEWNWNyP3kXElA";
    SharedPreferences sharedPreferences;
    JsonObjectRequest jsonObjectRequest;
    RequestQueueSingleton instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // je crée la queue
        // je créer un shared preferences et on get l'id de l'utilisateur
        sharedPreferences = getApplicationContext().getSharedPreferences("SPOTIFY", 0);
        instance = RequestQueueSingleton.getInstance(this);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
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
        instance.addToRequestQueue(jsonObjectRequest);
    }
}