package com.example.quizspotify;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.internal.LinkedTreeMap;

public class Question2 extends AppCompatActivity {

    Requete requete;
    Question question2;
    NetworkImageView imageAlbum1, imageAlbum2;
    TextView textViewAlbum1,textViewAlbum2,textViewQuestion2,textViewAnswer1,textViewAnswer2;
    Button buttonHerLoss,buttonDarkLane;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);


        // les find view by id
        imageAlbum1 = findViewById(R.id.imageViewAlbum1);
        imageAlbum2 = findViewById(R.id.imageViewAlbum2);
        textViewAlbum1 = findViewById(R.id.textViewAlbum1);
        textViewAlbum2 = findViewById(R.id.textViewAlbum2);
        textViewQuestion2 = findViewById(R.id.textViewQuestion2);
        textViewAnswer1 = findViewById(R.id.textViewAnswer1);
        textViewAnswer2 = findViewById(R.id.textViewAnswer2);
        buttonHerLoss = findViewById(R.id.buttonHerLoss);
        buttonDarkLane = findViewById(R.id.buttonDarkLane);

        ec = new Ecouteur();

        requete = new Requete(this,"https://api.spotify.com/v1/albums/5MS3MvWHJ3lOZPLiMxzOU6?si=sfSl7HARTUC4eEWCjTTqzA",
                "https://api.spotify.com/v1/albums/6OQ9gBfg5EXeNAEwGSs6jK?si=AAoAWL6tTTyG5Y0St--j7g");
        requete.ajouter_requête();

        question2 = new Question("Quel album est le plus récent ?","Album");
        textViewQuestion2.setText(question2.getQuestion());


        buttonHerLoss.setOnClickListener(ec);
        buttonDarkLane.setOnClickListener(ec);

    }


    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (question2.verifierReponse(((Button)v).getText().toString())) {
                Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                textViewAnswer1.setVisibility(View.VISIBLE);
                textViewAnswer2.setVisibility(View.VISIBLE);
                textViewAnswer1.setText(String.format("Date de sortie : %s", question2.getReponse1()));
                textViewAnswer2.setText(String.format("Date de sortie : %s", question2.getReponse2()));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent startNewActivity = new Intent(Question2.this, Question3.class);
                        startActivity(startNewActivity);
                    }
                },3000);
            }
            else {
                Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                new android.os.Handler().postDelayed(Question2.this::recreate,3000);
            }
        }
    }

    public void afficherInfos(Album album){
        if (album.getName().equals("Her Loss")){
            textViewAlbum1.setText(album.getName());
            imageAlbum1.setImageUrl((String) ((LinkedTreeMap)album.getImages().get(0)).get("url"),requete.getInstance().getImageLoader());
            question2.setReponse1(album.getRelease_date());
        }
        else{
            textViewAlbum2.setText(album.getName());
            imageAlbum2.setImageUrl((String) ((LinkedTreeMap)album.getImages().get(0)).get("url"),requete.getInstance().getImageLoader());
            question2.setReponse2(album.getRelease_date());
        }
    }
}
