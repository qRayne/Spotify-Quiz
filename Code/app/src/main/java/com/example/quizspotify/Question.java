package com.example.quizspotify;

import android.content.SharedPreferences;

import com.android.volley.toolbox.NetworkImageView;

import java.util.Vector;

public class Question {
    private String question;
    private String typeQuestion;
    private String reponse1;
    private String reponse2;

    public Question(String question, String typeQuestion) {
        this.question = question;
        this.typeQuestion = typeQuestion;
    }

    public boolean verifierReponse(String reponse){
        boolean bonneReponse = false;

        switch (typeQuestion) {
            case "Artiste":
                if (question.equals("Quel artiste est le plus populaire ?"))
                    if (reponse.equals("Pop Smoke"))
                        bonneReponse = true;
                break;
            case "Album":
                if (question.equals("Quel album est le plus récent ?")){
                    if (reponse.equals("Her loss"))
                        bonneReponse = true;
                }
                break;
            case "Chanson":

                break;
        }
        return bonneReponse;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(String typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public String getReponse1() {
        return reponse1;
    }

    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public void setReponse2(String response2) {
        this.reponse2 = response2;
    }
}
