package com.example.quizspotify;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Requete {
    private SharedPreferences sharedPreferences;
    private RequestQueueSingleton instance;
    private Context context;
    private Vector<JSONObject> jsonObjectsVector;
    private Vector<Object> vectorElements;
    private String typeElement;
    int compteur = 0;

    public Requete(Context context,String typeElement) {
        this.context = context;
        this.sharedPreferences = context.getApplicationContext().getSharedPreferences("SPOTIFY",0);
        this.instance = RequestQueueSingleton.getInstance(context);
        this.vectorElements = new Vector<>();
        this.jsonObjectsVector = new Vector<JSONObject>();
        this.typeElement = typeElement;
    }

    public JsonObjectRequest creer_requête(String url){
        return new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                compteur++;
                jsonObjectsVector.add(response);

                if (compteur == 2) {
                    try {
                        remplirVector(((Question1)context).recupererVector(jsonObjectsVector));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
                , null){
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

    public void ajouter_requête(String urlArtiste1,String urlArtiste2){
        // On ajoute le premier artiste et le second artiste
        instance.addToRequestQueue(creer_requête(urlArtiste1));
        instance.addToRequestQueue(creer_requête(urlArtiste2));
    }


    public void remplirVector(Vector<JSONObject> vector) throws JSONException {
        switch (typeElement) {
            case "Artiste":
                for (int i = 0; i < jsonObjectsVector.size(); i++) {
                    vectorElements.add(new Artiste(jsonObjectsVector.get(i).get("name").toString(), Integer.parseInt(jsonObjectsVector.get(i).get("popularity").toString()),
                            Integer.parseInt(jsonObjectsVector.get(i).getJSONObject("followers").get("total").toString()), jsonObjectsVector.get(i).getJSONArray("genres").get(0).toString(),
                            jsonObjectsVector.get(i).getJSONArray("images").getJSONObject(0).get("url").toString()));
                }
                break;
            case "Chanson":
                System.out.println("Chanson");
                break;
            case "Album":
                System.out.println("Album");
                break;
        }
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

    public Vector<JSONObject> getJsonObjectsVector() {
        return jsonObjectsVector;
    }

    public void setJsonObjectsVector(Vector<JSONObject> jsonObjectsVector) {
        this.jsonObjectsVector = jsonObjectsVector;
    }

    public Vector<Object> getVectorElements() {
        return vectorElements;
    }

    public void setVectorElements(Vector<Object> vectorElements) {
        this.vectorElements = vectorElements;
    }
}
