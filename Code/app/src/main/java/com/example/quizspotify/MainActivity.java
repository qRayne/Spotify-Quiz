package com.example.quizspotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    String urlArtiste1 = "https://api.spotify.com/v1/artists/0eDvMgVFoNV3TpwtrVCoTj?si=c6jzRYGwSEWNWNyP3kXElA";
    String urlArtiste2 = "https://api.spotify.com/v1/artists/6UCQYrcJ6wab6gnQ89OJFh?si=HC6fuPcKQ4enudg0_oykzQ";
    SharedPreferences sharedPreferences;
    JsonObjectRequest jsonObjectRequest1,jsonObjectRequest2;
    RequestQueueSingleton instance;
    TextView nomArtiste1,noPopularite1,nbFollowers1,genreMusic1,nomArtiste2,noPopularite2,nbFollowers2,genreMusic2;
    NetworkImageView imageArtiste1, imageArtiste2;
    Vector<Artiste> vectorArtiste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // les initialisations
        nomArtiste1 = findViewById(R.id.nomArtiste1);
        noPopularite1 = findViewById(R.id.noPopularite1);
        nbFollowers1 = findViewById(R.id.nbFollowers1);
        genreMusic1 = findViewById(R.id.genreMusic1);
        nomArtiste2 = findViewById(R.id.nomArtiste2);
        noPopularite2 = findViewById(R.id.noPopularite2);
        nbFollowers2 = findViewById(R.id.nbFollowers2);
        genreMusic2 = findViewById(R.id.genreMusic2);
        imageArtiste1 = findViewById(R.id.imageArtiste1);
        imageArtiste2 = findViewById(R.id.imageArtiste2);
        vectorArtiste = new Vector<>();

        // je crée la queue
        // je créer un shared preferences et on get l'id de l'utilisateur
        sharedPreferences = getApplicationContext().getSharedPreferences("SPOTIFY", 0);
        instance = RequestQueueSingleton.getInstance(this);

        jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET,
                urlArtiste1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // on manipule le data avec notre fonction
                try {
                    onResponseRequest(response);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
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

        jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET,
                urlArtiste2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // on manipule le data avec notre fonction
                try {
                    onResponseRequest(response);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
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

        instance.addToRequestQueue(jsonObjectRequest1);
        instance.addToRequestQueue(jsonObjectRequest2);
    }

    private void onResponseRequest(JSONObject reponse) throws JSONException, IOException {
        vectorArtiste.add(new Artiste(reponse.get("name").toString(), Integer.parseInt(reponse.get("popularity").toString()),
                Integer.parseInt(reponse.getJSONObject("followers").get("total").toString()), reponse.getJSONArray("genres").get(0).toString(),
                reponse.getJSONArray("images").getJSONObject(0).get("url").toString()));

        for (int i = 0 ; i< vectorArtiste.size(); i++) {
            if (i == 0) {
                nomArtiste1.setText(String.format("le nom de l'artiste : %s", vectorArtiste.get(i).getNomArtiste()));
                noPopularite1.setText(String.format("son classement dans son pays : %s", vectorArtiste.get(i).getNoPopularite()));
                nbFollowers1.setText(String.format("son nombre de followers : %s", vectorArtiste.get(i).getNbFollowers()));
                genreMusic1.setText(String.format("son genre de musique : \n %s", vectorArtiste.get(i).getGenreMusic()));
                imageArtiste1.setImageUrl(vectorArtiste.get(i).getImageArtiste(),instance.getImageLoader());
            } else {
                nomArtiste2.setText(String.format("le nom de l'artiste : %s", vectorArtiste.get(i).getNomArtiste()));
                noPopularite2.setText(String.format("son classement dans son pays : %s", vectorArtiste.get(i).getNoPopularite()));
                nbFollowers2.setText(String.format("son nombre de followers : %s", vectorArtiste.get(i).getNbFollowers()));
                genreMusic2.setText(String.format("son genre de musique : \n %s", vectorArtiste.get(i).getGenreMusic()));
                imageArtiste2.setImageUrl(vectorArtiste.get(i).getImageArtiste(),instance.getImageLoader());
            }
        }
    }
}