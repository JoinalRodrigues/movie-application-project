package com.niit.recommended.service;

import com.niit.recommended.domain.*;

import java.util.List;

public interface RecommendedForUser {

    public List<Movie> getRecommendedList();


    public List<Genre> getGenreList();

    public List<Movie> getMovieListByGenreName(String name);

    public List<Movie> getMovieListByMovieName(String movieName);

    public List<Movie> searchMovieListByMovieName(String movieName);


    public List<Cast> getCastForMovies(String name);
    public List<Movie> getMovieListActionGenre();
    public List<Movie> getMovieListComedyGenre();
    public List<Movie> getMovieListCrimeGenre();
    public List<Movie> getMovieListFamilyGenre();

    public List<Trailer> getMovieTrailer(String movieName);

    public List<Movie> upcomingMovieList();
    public Object getMovieInfoData(String movieName);


//    public String getMovieIdByGenre(String name);




}
