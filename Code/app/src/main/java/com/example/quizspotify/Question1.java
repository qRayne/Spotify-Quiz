package com.example.quizspotify;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.internal.LinkedTreeMap;

public class Question1 extends AppCompatActivity {
    Requete requete;
    Question question1;
    NetworkImageView imageArtiste1, imageArtiste2;
    TextView textViewArtiste1,textViewArtiste2,textViewQuestion1,textViewNbFollowers1,textViewNbFollowers2;
    Button buttonPopSmoke,buttonHeadieOne;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        // les setters
        imageArtiste1 = findViewById(R.id.imageViewAlbum1);
        imageArtiste2 = findViewById(R.id.imageViewArtiste2);
        textViewArtiste1 = findViewById(R.id.textViewNomArtiste1);
        textViewArtiste2 = findViewById(R.id.textViewNomArtiste2);
        textViewQuestion1 = findViewById(R.id.textViewQuestion1);
        textViewNbFollowers1 = findViewById(R.id.textViewNbFollowers1);
        textViewNbFollowers2 = findViewById(R.id.textViewNbFollowers2);
        buttonPopSmoke = findViewById(R.id.buttonPopSmoke);
        buttonHeadieOne = findViewById(R.id.buttonHeadieOne);
        ec = new Ecouteur();

        requete = new Requete(this,"https://api.spotify.com/v1/artists/0eDvMgVFoNV3TpwtrVCoTj?si=c6jzRYGwSEWNWNyP3kXElA",
                "https://api.spotify.com/v1/artists/6UCQYrcJ6wab6gnQ89OJFh?si=HC6fuPcKQ4enudg0_oykzQ");
        requete.ajouter_requête();

        question1 = new Question("Quel artiste est le plus populaire ?","Artiste");
        textViewQuestion1.setText(question1.getQuestion());

        buttonPopSmoke.setOnClickListener(ec);
        buttonHeadieOne.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (question1.verifierReponse(((Button)view).getText().toString())) {
                Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                textViewNbFollowers1.setVisibility(View.VISIBLE);
                textViewNbFollowers2.setVisibility(View.VISIBLE);
                textViewNbFollowers1.setText(String.format("%s Followers", question1.getReponse1()));
                textViewNbFollowers2.setText(String.format("%s Followers", question1.getReponse2()));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent startNewActivity = new Intent(Question1.this, Question2.class);
                        startActivity(startNewActivity);
                    }
                },3000);
            }
            else {
                Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                new android.os.Handler().postDelayed(Question1.this::recreate,3000);
            }
        }
    }

    public void afficherInfos(Artiste artiste){
        if (artiste.getNomArtiste().equals("Pop Smoke")){
            textViewArtiste1.setText(artiste.getNomArtiste());
            imageArtiste1.setImageUrl((String) ((LinkedTreeMap)artiste.getImages().get(0)).get("url"),requete.getInstance().getImageLoader());
            question1.setReponse1(String.valueOf(artiste.getFollowers().get("total")));
        }
        else{
            textViewArtiste2.setText(artiste.getNomArtiste());
            imageArtiste2.setImageUrl((String) ((LinkedTreeMap)artiste.getImages().get(0)).get("url"),requete.getInstance().getImageLoader());
            question1.setReponse2(String.valueOf(artiste.getFollowers().get("total")));
        }
    }
}
