package com.niit.recommended.domain;

import java.util.List;

public class MovieInfo {

    private List<Movie> movieList;
   // private List<Genre> genreList;
    private List<Cast> castList;
    private List<Trailer> trailerList;

    public MovieInfo(List<Movie> movieList, List<Cast> castList, List<Trailer> trailerList) {
        this.movieList = movieList;
        //this.genreList = genreList;
        this.castList = castList;
        this.trailerList = trailerList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

//    public List<Genre> getGenreList() {
//        return genreList;
//    }
//
//    public void setGenreList(List<Genre> genreList) {
//        this.genreList = genreList;
//    }

    public List<Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }


    @Override
    public String toString() {
        return "MovieInfo{" +
                "movieList=" + movieList +
             //   ", genreList=" + genreList +
                ", castList=" + castList +
                ", trailerList=" + trailerList +
                '}';
    }
}
