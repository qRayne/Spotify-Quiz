package com.example.quizspotify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    Button question1,question2,question3;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        question1 = findViewById(R.id.buttonQuestion1);
        question2 = findViewById(R.id.buttonQuestion2);
        question3 = findViewById(R.id.buttonQuestion3);
        ec = new Ecouteur();

        question1.setOnClickListener(ec);
        question2.setOnClickListener(ec);
        question3.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (view.equals(question1))
                startActivity(new Intent(StartActivity.this,Question1.class));
            else if (view.equals(question2))
                startActivity(new Intent(StartActivity.this,Question2.class));
            else
                startActivity(new Intent(StartActivity.this,Question3.class));
        }
    }
}
