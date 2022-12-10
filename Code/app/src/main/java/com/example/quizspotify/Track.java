package com.example.quizspotify;

import java.util.ArrayList;

public class Track {
    private String name;
    private int popularity;
    private String preview_url;
    private String image;

    public Track(String name, int popularity, String preview_url, String image) {
        this.name = name;
        this.popularity = popularity;
        this.preview_url = preview_url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public String getImage() {
        return image;
    }

    public void setImages(String image) {
        this.image = image;
    }
}
