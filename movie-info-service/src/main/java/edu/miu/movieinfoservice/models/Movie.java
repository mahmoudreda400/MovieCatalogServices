package edu.miu.movieinfoservice.models;

public class Movie {

    private int id;
    private String movieId;
    private String movieName;

    public Movie(int id, String movieId, String movieName) {
        this.id = id;
        this.movieId = movieId;
        this.movieName = movieName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
