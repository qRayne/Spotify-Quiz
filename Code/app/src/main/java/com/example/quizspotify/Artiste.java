package com.example.quizspotify;

import java.util.ArrayList;
import java.util.Map;

public class Artiste {
    private String name;
    private ArrayList images;
    private Map<String,Integer> followers;


    public Artiste(String name, ArrayList images,Map<String,Integer> followers) {
        this.name = name;
        this.images = images;
        this.followers = followers;
    }


    public String getNomArtiste() {
        return name;
    }

    public void setNomArtiste(String nomArtiste) {
        this.name = nomArtiste;
    }

    public ArrayList getImages() {
        return images;
    }

    public void setImages(ArrayList images) {
        this.images = images;
    }

    public Map<String, Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(Map<String, Integer> followers) {
        this.followers = followers;
    }
}
