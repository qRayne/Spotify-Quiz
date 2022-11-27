package com.example.quizspotify;

import java.util.ArrayList;

public class Artiste {
    private String nomArtiste;
    private int noPopularite;
    private int nbFollowers;
    private String genreMusic;
    private String imageArtiste;

    public Artiste(String nomArtiste, int noPopularite, int nbFollowers, String genreMusic, String imageArtiste) {
        this.nomArtiste = nomArtiste;
        this.noPopularite = noPopularite;
        this.nbFollowers = nbFollowers;
        this.genreMusic = genreMusic;
        this.imageArtiste = imageArtiste;
    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public int getNoPopularite() {
        return noPopularite;
    }

    public void setNoPopularite(int noPopularite) {
        this.noPopularite = noPopularite;
    }

    public int getNbFollowers() {
        return nbFollowers;
    }

    public void setNbFollowers(int nbFollowers) {
        this.nbFollowers = nbFollowers;
    }

    public String getGenreMusic() {
        return genreMusic;
    }

    public void setGenreMusic(String genreMusic) {
        this.genreMusic = genreMusic;
    }

    public String getImageArtiste() {
        return imageArtiste;
    }

    public void setImageArtiste(String imageArtiste) {
        this.imageArtiste = imageArtiste;
    }
}
