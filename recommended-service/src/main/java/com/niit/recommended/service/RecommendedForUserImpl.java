package com.niit.recommended.service;

import com.niit.recommended.domain.*;
import com.niit.recommended.repository.MovieRecommendedRepository;
import com.niit.recommended.repository.ThirdPartyApiRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class RecommendedForUserImpl implements RecommendedForUser{



    private MovieRecommendedRepository movieRepository;
    private ThirdPartyApiRepository thirdPartyApiRepository;


    @Autowired
    public RecommendedForUserImpl(MovieRecommendedRepository movieRepository , ThirdPartyApiRepository thirdPartyApiRepository) {
        this.movieRepository = movieRepository;
        this.thirdPartyApiRepository = thirdPartyApiRepository;
    }



    public Object getMovieInfoData(String movieName)
    {

        //List<Movie> movieList = new ArrayList<>();
        List<Movie> movieList = thirdPartyApiRepository.searchMovieListByMovieNameOne(movieName);
              //String movieId = movieList.get(0).getMovieId();
         String id=thirdPartyApiRepository.getMovieIdByMovieName(movieName,movieList);
              List<Cast> castList = thirdPartyApiRepository.getMovieCredits(id);
              List<Trailer> trailerList = thirdPartyApiRepository.getMovieTrailer(id);

            MovieInfo movieInfo = new MovieInfo(movieList,castList,trailerList);
              //movieInfoList.addAll((Collection<? extends MovieInfo>) Arrays.asList(movieList));

              return  movieInfo;

    }


    @Override
    public List<Movie> getRecommendedList() {


           List<Movie> movies = movieRepository.getMovieList();

           List<Movie> newMovies = movies.stream().limit(10).toList();

        return newMovies;
    }

    @Override
    public List<Genre> getGenreList() {

        List<Genre> genres = movieRepository.getGenreList();
        List<Genre> genreList = genres.stream().limit(10).toList();
        return genreList;
    }



   @Override
    public List<Movie> getMovieListByGenreName(String name) {

       List<Genre> genres = movieRepository.getMovieGenreList();
       String id = movieRepository.getGenreIdByMovieName(name, genres);
       List<Movie> movies = movieRepository.movieListByGenreName(id);

       return movies;
   }
   @Override
    public List<Movie> getMovieListByMovieName(String movieName) {

        List<Movie> movie= movieRepository.getMovieList();
        List<Movie> movies=movieRepository.getMovieByMovieName(movieName,movie);

        return movies;
    }



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
                .map(i -> new Movie(i.get("id").toString()
                        ,i.get("original_title").toString()
                        ,i.get("overview").toString()
                        ,i.get("poster_path").toString()
                        ,i.get("vote_average").toString()
                        ,i.get("release_date").toString()))
               .limit(1)
                .toList();


        return movie;
    }



    public List<Cast> getCastForMovies(String name){

        List<Movie> movies = thirdPartyApiRepository.searchMovieListByMovieName(name);
        String id = thirdPartyApiRepository.getMovieIdByMovieName(name , movies);
        List<Cast> casts = thirdPartyApiRepository.getMovieCredits(id);

        return casts;

    }



    public List<Trailer> getMovieTrailer(String movieName)
    {
        List<Movie> movies = thirdPartyApiRepository.searchMovieListByMovieName(movieName);
        String id = thirdPartyApiRepository.getMovieIdByMovieName(movieName , movies);
        List<Trailer> trailer = thirdPartyApiRepository.getMovieTrailer(id);
        return  trailer;
    }

    public List<Movie> upcomingMovieList()
    {
        List<Movie> upcomingMovieList = thirdPartyApiRepository.upcomingMovieList();
        return upcomingMovieList;
    }

    public List<Movie> getMovieListActionGenre()
    {
        String actionId = "28";
        List<Movie> movies = thirdPartyApiRepository.movieListforDifferentGenre(actionId);
        return movies;
    }
    public List<Movie> getMovieListComedyGenre()
    {
        String comedyId = "35";
        List<Movie> movies = thirdPartyApiRepository.movieListforDifferentGenre(comedyId); return movies;
    }
    public List<Movie> getMovieListCrimeGenre()
    {
        String crimeId = "80";
        List<Movie> movies = thirdPartyApiRepository.movieListforDifferentGenre(crimeId); return movies;
    }

    public List<Movie> getMovieListFamilyGenre()
    {
        String familyId = "10751";
        List<Movie> movies = thirdPartyApiRepository.movieListforDifferentGenre(familyId); return  movies;
    }




}
