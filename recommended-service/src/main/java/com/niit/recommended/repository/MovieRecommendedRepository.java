package com.niit.recommended.repository;

import com.niit.recommended.domain.Genre;
import com.niit.recommended.domain.Movie;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;


@Repository
public class MovieRecommendedRepository {


    private List<Movie> movieList = new ArrayList<>();

    private  List<Genre> genreList = new ArrayList<>();


    public List<Genre> getMovieGenreList()
    {
        String uri = "https://api.themoviedb.org/3/genre/movie/list?api_key=dd4d819639705d332d531217b4f7c6b6&language=en-US";
        RestTemplate restTemplate = new RestTemplate();
        JSONObject result = restTemplate.getForObject(uri, JSONObject.class);


        List<Genre> genres = ((List<LinkedHashMap>)result.get("genres"))
                .stream()
                .map((i) -> new Genre(Integer.parseInt(i.get("id").toString())
                        ,i.get("name").toString()))
                .toList();

        return genres;
    }


    public List<Movie> getMovieList()
    {
        String url1 = "https://api.themoviedb.org/3/movie/popular?api_key=dd4d819639705d332d531217b4f7c6b6&page=1&language=en-US&region=US";


        RestTemplate restTemplate = new RestTemplate();
        JSONObject result2 = restTemplate.getForObject(url1, JSONObject.class);

        List<Movie> movie = ((List<LinkedHashMap>)result2.get("results"))
                .stream()
                .map((i) -> new Movie(i.get("id").toString()
                        ,i.get("original_title").toString()
                        ,i.get("overview").toString()
                        ,i.get("poster_path").toString()
                        ,(i.get("vote_average").toString())
                        ,i.get("release_date").toString()))
                .toList();

        return movie;
    }


    public List<Movie> movieListByGenreName( String id)
    {
        String url1 = "https://api.themoviedb.org/3/genre/"+id+"/movies?api_key=dd4d819639705d332d531217b4f7c6b6";


        RestTemplate restTemplate = new RestTemplate();
        JSONObject result2 = restTemplate.getForObject(url1, JSONObject.class);

        List<Movie> movie = ((List<LinkedHashMap>)result2.get("results"))
                .stream()
                .map(i -> new Movie(
                        i.get("id").toString(),
                        i.get("original_title").toString(),
                        i.get("overview").toString(),
                        i.get("poster_path").toString(),(
                                i.get("vote_average").toString()),
                        i.get("release_date").toString()))
                .toList();

        return movie;
    }




    public String getGenreIdByMovieName(String name ,List<Genre> genres){


        String id = "";

        id = String.valueOf(genres.stream().filter(i -> i.getName().equals(name)).map(g -> g.getId()).findAny().get());


        return id;
    }



    public List<Movie> getMovieByMovieName(String movieName ,List<Movie> movies){

        List<Movie> movies1 = movies.stream().filter(i -> i.getOriginalTitle().equals(movieName)).toList();

        return movies1;
    }



    public List<Movie> getMovieList(Predicate<Movie> predicate)
    {


        return this.movieList.stream().filter(predicate).toList();


    }

    public  List<Genre> getGenreList()
    {
        return Collections.unmodifiableList(this.genreList);
    }

    public boolean clearMovieList(){

        this.movieList.clear();

        return true;
    }

    public boolean populateMovieList(List<Movie> movies)
    {
           this.movieList.addAll(movies);

        System.out.println(movieList);

           return true;
    }

    public  boolean populateGenreList(List<Genre> genres)
    {
        this.genreList.addAll(genres);
        return true;
    }



}
