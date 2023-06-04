package com.niit.recommended.repository;

import com.niit.recommended.domain.Cast;
import com.niit.recommended.domain.Movie;
import com.niit.recommended.domain.Trailer;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;


@Repository
public class ThirdPartyApiRepository {


    public List<Movie> searchMovieListByMovieName(String movieName)
    {
        String url1 = "https://api.themoviedb.org/3/search/movie?api_key=dd4d819639705d332d531217b4f7c6b6&query="+movieName;

        System.out.println(url1);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject result = restTemplate.getForObject(url1, JSONObject.class);

        System.out.println(result.get("results"));
        List<Movie> movie = ((List<Object>)result.get("results"))
                .stream()
                .map(i -> (LinkedHashMap)i)
                .map(i -> new Movie(
                         i.get("id").toString()
                         ,i.get("original_title").toString()
                        ,i.get("overview").toString()
                        ,i.get("poster_path").toString()
                        ,i.get("vote_average").toString()
                        ,i.get("release_date").toString()))
                .limit(1)
                .toList();


        return movie;
    }


    public List<Movie> searchMovieListByMovieNameOne(String movieName)
    {
        String url1 = "https://api.themoviedb.org/3/search/movie?api_key=dd4d819639705d332d531217b4f7c6b6&query="+movieName;

        System.out.println(url1);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject result = restTemplate.getForObject(url1, JSONObject.class);

        System.out.println(result.get("results"));
        List<Movie> movie = ((List<Object>)result.get("results"))
                .stream()
                .map(i -> (LinkedHashMap)i)
                .map(i -> new Movie(
                        i.get("id").toString()
                        ,i.get("original_title").toString()
                        ,i.get("overview").toString()
                        ,i.get("poster_path").toString()
                        ,i.get("vote_average").toString()
                        ,i.get("release_date").toString()))
                .limit(1)
                .toList();


        return movie;
    }




    public String getMovieIdByMovieName(String name ,List<Movie> movies){


        String id = "";

        id = String.valueOf(movies.stream().filter(i -> i.getOriginalTitle().equals(name)).map(g -> g.getMovieId()).findAny().get());


        return id;
    }



    public List<Cast> getMovieCredits(String movieId)
    {

        String url1 = "https://api.themoviedb.org/3/movie/"+movieId+"/credits?api_key=dd4d819639705d332d531217b4f7c6b6";

        System.out.println(url1);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject result = restTemplate.getForObject(url1, JSONObject.class);

        System.out.println(result.get("cast"));
        List<Cast> casts = ((List<Object>)result.get("cast"))
                .stream()
                .map(i -> (LinkedHashMap)i)
                .map(i -> new Cast(
                        i.get("name").toString()
                        ,i.get("known_for_department").toString()))
                .limit(5)
                .toList();



        return casts;
    }


    public List<Movie> movieListforDifferentGenre(String id)
    {

        String d = id;

        String url1 = "https://api.themoviedb.org/3/genre/"+d+"/movies?api_key=dd4d819639705d332d531217b4f7c6b6";


        RestTemplate restTemplate = new RestTemplate();
        JSONObject result2 = restTemplate.getForObject(url1, JSONObject.class);

        List<Movie> movie = ((List<LinkedHashMap>)result2.get("results"))
                .stream()
                .map(i -> new Movie(i.get("id").toString()
                        , i.get("original_title").toString()
                        ,i.get("overview").toString()
                        ,i.get("poster_path").toString()
                        ,(i.get("vote_average").toString())
                        ,i.get("release_date").toString()))
                .limit(10)
                .toList();

        return movie;
    }


    public List<Trailer> getMovieTrailer(String movieId)
    {
        String url1 = "https://api.themoviedb.org/3/movie/"+movieId+"/videos?api_key=dd4d819639705d332d531217b4f7c6b6";

        RestTemplate restTemplate = new RestTemplate();
        JSONObject result = restTemplate.getForObject(url1, JSONObject.class);

        List<Trailer> trailer = ((List<LinkedHashMap>)result.get("results"))
                .stream()
                .map(i -> new Trailer(i.get("key").toString()
                        , i.get("site").toString()
                        , i.get("id").toString()))
                .limit(1)
                .toList();

        return trailer;

    }


    public List<Movie> upcomingMovieList()
    {
        String url1 = "https://api.themoviedb.org/3/movie/upcoming?api_key=dd4d819639705d332d531217b4f7c6b6&page=1&language=en-US&region=US";

        System.out.println(url1);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject result = restTemplate.getForObject(url1, JSONObject.class);

        System.out.println(result.get("results"));
        List<Movie> movie = ((List<Object>)result.get("results"))
                .stream()
                .map(i -> (LinkedHashMap)i)
                .map(i -> new Movie(
                        i.get("id").toString()
                        ,i.get("original_title").toString()
                        ,i.get("overview").toString()
                        ,i.get("poster_path").toString()
                        ,i.get("vote_average").toString()
                        ,i.get("release_date").toString()))
                .limit(10)
                .toList();


        return movie;
    }



}



