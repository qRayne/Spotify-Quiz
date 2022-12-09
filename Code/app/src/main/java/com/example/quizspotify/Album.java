package com.example.quizspotify;

import java.util.ArrayList;

public class Album {
    private String name;
    private String release_date;
    private ArrayList images;

    public Album(String name, String release_date, ArrayList images) {
        this.name = name;
        this.release_date = release_date;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public ArrayList getImages() {
        return images;
    }

    public void setImages(ArrayList images) {
        this.images = images;
    }
}
