package com.example.quizspotify;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    ImageView imageViewScore;
    TextView textViewFinalScore;
    Button buttonRestart;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageViewScore = findViewById(R.id.imageViewScore);
        textViewFinalScore = findViewById(R.id.textViewFinalScore);
        buttonRestart = findViewById(R.id.buttonRestart);
        extras = getIntent().getExtras();

        // les animations
        ObjectAnimator.ofFloat(imageViewScore, View.ROTATION, 0f, 360f).setDuration(5000).start();

        // afficher le score
        textViewFinalScore.setText(String.format("Votre score est de : %s", extras.get("Score")));

        buttonRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, Question1.class);
            startActivity(intent);
        });

    }
}