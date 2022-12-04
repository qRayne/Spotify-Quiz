package com.example.quizspotify;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class Question1 extends AppCompatActivity {
    private Requete requete;
    private Question question1;
    private NetworkImageView imageArtiste1, imageArtiste2;
    private TextView textViewArtiste1,textViewArtiste2,textViewQuestion1;
    private Button buttonA,buttonB;
    private Ecouteur ec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        // les setters
        imageArtiste1 = findViewById(R.id.imageArtiste1);
        imageArtiste2 = findViewById(R.id.imageArtiste2);
        textViewArtiste1 = findViewById(R.id.textViewArtiste1);
        textViewArtiste2 = findViewById(R.id.textViewArtiste2);
        textViewQuestion1 = findViewById(R.id.textViewQuestion1);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        ec = new Ecouteur();

        requete = new Requete(this,"Artiste");
        question1 = new Question("https://api.spotify.com/v1/artists/0eDvMgVFoNV3TpwtrVCoTj?si=c6jzRYGwSEWNWNyP3kXElA",
                "https://api.spotify.com/v1/artists/6UCQYrcJ6wab6gnQ89OJFh?si=HC6fuPcKQ4enudg0_oykzQ","Quel artiste est le plus populaire ?");

        requete.ajouter_requête(question1.getUrlArtiste1(),question1.getUrlArtiste2());
        textViewQuestion1.setText(question1.getQuestion());
        textViewArtiste1.setText("Pop Smoke");
        textViewArtiste2.setText("Headie One");

//        imageArtiste1.setImageUrl(question1.getUrlArtiste1(),requete.getInstance().getImageLoader());
//        imageArtiste2.setImageUrl(question1.getUrlArtiste1(),requete.getInstance().getImageLoader());
        buttonA.setOnClickListener(ec);
        buttonB.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (view.equals(buttonA))
                Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
        }
    }
}
