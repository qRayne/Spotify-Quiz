package com.example.quizspotify;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    Button start;
    ImageView logoSpotify,logoQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // les find view by id
        start = findViewById(R.id.buttonStart);
        logoSpotify = findViewById(R.id.logoSpotify);
        logoQuiz = findViewById(R.id.imageViewQuizLogo);

        // les animations
        ObjectAnimator.ofFloat(logoSpotify, View.ROTATION, 0f, 360f).setDuration(5000).start();
        ObjectAnimator.ofFloat(logoQuiz, View.TRANSLATION_X, -1000f, 0f).setDuration(5000).start();


        start.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, Question1.class);
            startActivity(intent);
        });
    }
}
