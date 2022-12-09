package com.example.quizspotify;

import android.os.Bundle;
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
    Button buttonA,buttonB;
    Ecouteur ec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        // les setters
        imageArtiste1 = findViewById(R.id.imageViewArtiste1);
        imageArtiste2 = findViewById(R.id.imageViewArtiste2);
        textViewArtiste1 = findViewById(R.id.textViewNomArtiste1);
        textViewArtiste2 = findViewById(R.id.textViewNomArtiste2);
        textViewQuestion1 = findViewById(R.id.textViewQuestion1);
        textViewNbFollowers1 = findViewById(R.id.textViewNbFollowers1);
        textViewNbFollowers2 = findViewById(R.id.textViewNbFollowers2);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        ec = new Ecouteur();

        requete = new Requete(this);
        question1 = new Question("https://api.spotify.com/v1/artists/0eDvMgVFoNV3TpwtrVCoTj?si=c6jzRYGwSEWNWNyP3kXElA",
                "https://api.spotify.com/v1/artists/6UCQYrcJ6wab6gnQ89OJFh?si=HC6fuPcKQ4enudg0_oykzQ","Quel artiste est le plus populaire ?");

        requete.ajouter_requête(question1.getUrlArtiste1(),question1.getUrlArtiste2());
        textViewQuestion1.setText(question1.getQuestion());

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

    public void afficherInfos(Artiste artiste){
        if (artiste.getNomArtiste().equals("Pop Smoke")){
            textViewArtiste1.setText(artiste.getNomArtiste());
            textViewNbFollowers1.setText(String.valueOf(artiste.getFollowers().get("total")));
            imageArtiste1.setImageUrl((String) ((LinkedTreeMap)artiste.getImages().get(0)).get("url"),requete.getInstance().getImageLoader());
        }
        else{
            textViewArtiste2.setText(artiste.getNomArtiste());
            textViewNbFollowers2.setText(String.valueOf(artiste.getFollowers().get("total")));
            imageArtiste2.setImageUrl((String) ((LinkedTreeMap)artiste.getImages().get(0)).get("url"),requete.getInstance().getImageLoader());
        }
    }
}
