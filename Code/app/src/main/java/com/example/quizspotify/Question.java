package com.example.quizspotify;

import android.content.SharedPreferences;

import com.android.volley.toolbox.NetworkImageView;

import java.util.Vector;

public class Question {
    private String urlArtiste1;
    private String urlArtiste2;
    private String question;

    public Question(String urlArtiste1, String urlArtiste2) {
        this.urlArtiste1 = urlArtiste1;
        this.urlArtiste2 = urlArtiste2;
        this.question = "Quel artiste est le plus populaire ?";
    }

    public boolean verifierReponse(String reponse){
        return reponse.equals("Pop Smoke");
    }

    public String getUrlArtiste1() {
        return urlArtiste1;
    }

    public void setUrlArtiste1(String urlArtiste1) {
        this.urlArtiste1 = urlArtiste1;
    }

    public String getUrlArtiste2() {
        return urlArtiste2;
    }

    public void setUrlArtiste2(String urlArtiste2) {
        this.urlArtiste2 = urlArtiste2;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
