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

public class Question3 extends AppCompatActivity {

    Requete requete;
    Question question3;
    NetworkImageView imageTrack1, imageTrack2;
    TextView textViewTrack1,textViewTrack2,textViewQuestion3,textViewAnswerTrack1,textViewAnswerTrack2;
    Button buttonSnitch,buttonFacetime;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        imageTrack1 = findViewById(R.id.imageViewTrack1);
        imageTrack2 = findViewById(R.id.imageViewTrack2);
        textViewTrack1 = findViewById(R.id.textViewTrack1);
        textViewTrack2 = findViewById(R.id.textViewTrack2);
        textViewQuestion3 = findViewById(R.id.textViewQuestion3);
        textViewAnswerTrack1 = findViewById(R.id.textViewAnswerTrack1);
        textViewAnswerTrack2 = findViewById(R.id.textViewAnswerTrack2);
        buttonSnitch = findViewById(R.id.buttonSnitch);
        buttonFacetime = findViewById(R.id.buttonFacetime);

        ec = new Ecouteur();

        requete = new Requete(this,"https://api.spotify.com/v1/tracks/3WaDoMDQRqDdgtCDLxanpN?si=78a3a0aca1cc4420",
                "https://api.spotify.com/v1/tracks/6gHWMJzCYkbDcsqKHoycJw?si=4a2c0cb4c3cc42d7");
        requete.ajouter_requête();

        question3 = new Question("Quel Track à été la plus populaire chez l'artiste 21 Savage ?","Track");
        textViewQuestion3.setText(question3.getQuestion());

        buttonSnitch.setOnClickListener(ec);
        buttonFacetime.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (question3.verifierReponse(((Button)v).getText().toString())) {
                Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                textViewAnswerTrack1.setVisibility(View.VISIBLE);
                textViewAnswerTrack2.setVisibility(View.VISIBLE);
                textViewAnswerTrack1.setText(String.format("%se Track la plus populaire chez 21 Savage", question3.getReponse1()));
                textViewAnswerTrack2.setText(String.format("%se Track la plus populaire chez 21 Savage", question3.getReponse2()));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent startNewActivity = new Intent(Question3.this, StartActivity.class);
                        startActivity(startNewActivity);
                    }
                },3000);
            }
            else {
                Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                new android.os.Handler().postDelayed(Question3.this::recreate,3000);
            }
        }
    }



    public void afficherInfos(Track track){
        if (track.getName().equals("Snitches & Rats (feat. Young Nudy)")){
            textViewTrack1.setText(track.getName());
            imageTrack1.setImageUrl(track.getImage(),requete.getInstance().getImageLoader());
            question3.setReponse1(String.valueOf(track.getPopularity()));
        }
        else{
            textViewTrack2.setText(track.getName());
            imageTrack2.setImageUrl(track.getImage(),requete.getInstance().getImageLoader());
            question3.setReponse2(String.valueOf(track.getPopularity()));
        }

    }
}