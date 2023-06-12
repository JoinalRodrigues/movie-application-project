package com.niit.recommended.controller;

import com.niit.recommended.domain.*;

import com.niit.recommended.dto.MessageDTO;
import com.niit.recommended.exception.InvalidCredentialsException;
import com.niit.recommended.exception.TokenExpiredException;
import com.niit.recommended.service.RecommendedForUser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.springframework.cglib.core.TypeUtils.add;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class RecommendedController {

    private ResponseEntity<?> responseEntity;

    private final RecommendedForUser recommendedForUser;

    @ApiResponse(description = "Get(), gets genre list")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/genre")
    public List<Genre> movieListGenre()
    {
        List<Genre> genres=recommendedForUser.getGenreList();
        return genres;
    }

    @ApiResponse(description = "Get(), gets recommended movie list")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/popularMovie")
    public List<Movie> getRecommendedMovieList()
    {
        List<Movie> movies=recommendedForUser.getRecommendedList();
        return movies;
    }

    @ApiResponse(description = "Get(genreName), gets movie list by genre name")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/{name}")
    public List<Movie> movieListByGenreName(@PathVariable String name)
    {
        List<Movie> movie=recommendedForUser.getMovieListByGenreName(name);
        return movie;
    }

    @ApiResponse(description = "Get(movieName), gets movie list by movieName")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/movie/{movieName}")
    public List<Movie> movieListByMovieName(@PathVariable String movieName)
    {
        List<Movie> movie = recommendedForUser.getMovieListByMovieName(movieName);
        return movie;
    }

    @ApiResponse(description = "Get(searchName), returns list of movie matching search name")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/searchMovie/{movieName}" )  //Search Function by giving MovieName
    public List<Movie> searchMovieListByMovieName(@PathVariable String movieName)
    {

       List<Movie> searchMovieList = recommendedForUser.searchMovieListByMovieName(movieName);

       return  searchMovieList;
    }

    @ApiResponse(description = "Get(movieName), gets cast of movie")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/cast/{name}")
    public List<Cast> getCastForMovies(@PathVariable String name)
    {
        List<Cast> castsLists = recommendedForUser.getCastForMovies(name);
        return castsLists;
    }

    @ApiResponse(description = "Get(movieName), gets specific movie data")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/{movieName}")
    public Object getMovieInfoData(String movieName)
    {


        Object movieInfoList = recommendedForUser.getMovieInfoData(movieName);
        return  movieInfoList;

    }


    @ApiResponse(description = "Get(), gets upcoming movies list")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/upcomingMovies")
    public List<Movie> upcomingMovieList()
    {
        List<Movie> upcomingMovieList = recommendedForUser.upcomingMovieList();
        return upcomingMovieList;
    }

    @ApiResponse(description = "Get(movieName), gets movie trailer")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/trailer/{name}")
    public List<Trailer> getMovieTrailer(@PathVariable String name)
    {
        List<Trailer> trailer = recommendedForUser.getMovieTrailer(name);
        return trailer;
    }

    @ApiResponse(description = "Get(), gets movie list belonging to action genre")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/Action")
    public List<Movie> getMovieListActionGenre()
    {
        List<Movie> actionMovieList = recommendedForUser.getMovieListActionGenre();
        return actionMovieList;
    }

    @ApiResponse(description = "Get(), gets movie list belonging to comedy genre")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/Comedy")
    public List<Movie> getMovieListComedyGenre()
    {
        List<Movie> comedyMovieList = recommendedForUser.getMovieListComedyGenre();
        return comedyMovieList;
    }

    @ApiResponse(description = "Get(), gets movie list belonging to crime genre")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/Crime")
    public List<Movie> getMovieListCrimeGenre()
    {
        List<Movie> crimeMovieList = recommendedForUser.getMovieListCrimeGenre();
        return crimeMovieList;
    }

    @ApiResponse(description = "Get(), gets movie list belonging to family genre")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/Family")
    public List<Movie> getMovieListFamilyGenre()
    {
        List<Movie> familyMovieList = recommendedForUser.getMovieListFamilyGenre();
        return familyMovieList;
    }

    public CompletableFuture<ResponseEntity<MessageDTO>> fallback(Exception e) throws Exception {
        if(e instanceof InvalidCredentialsException)
            throw e;
        if(e instanceof TokenExpiredException)
            throw e;
        e.printStackTrace();
        return CompletableFuture.completedFuture(new ResponseEntity<>(new MessageDTO("Server error, please try in some time"), HttpStatus.INTERNAL_SERVER_ERROR));
    }





//    @GetMapping(value = "/thirdParty")
//    public List<Movie> movieListforDifferentGenre()
//    {
//
//        String id="28";
//        String url1 = "https://api.themoviedb.org/3/genre/"+id+"/movies?api_key=dd4d819639705d332d531217b4f7c6b6";
//
//
//        RestTemplate restTemplate = new RestTemplate();
//        JSONObject result2 = restTemplate.getForObject(url1, JSONObject.class);
//
//        List<Movie> movie = ((List<LinkedHashMap>)result2.get("results"))
//                .stream()
//                .map(i -> new Movie(i.get("id").toString()
//                        , i.get("original_title").toString()
//                        ,i.get("overview").toString()
//                        ,i.get("poster_path").toString()
//                        ,(i.get("vote_average").toString())
//                        ,i.get("release_date").toString()))
//                .limit(10)
//                .toList();
//
//        return movie;
//    }






//    @GetMapping(value = "/clientHello1")
//    private List<Movie> getMovieDetails() {
//
//        String uri = "https://api.themoviedb.org/3/movie/popular?api_key=dd4d819639705d332d531217b4f7c6b6&page=1&language=en-US&region=US";
//        RestTemplate restTemplate = new RestTemplate();
//        JSONObject result = restTemplate.getForObject(uri, JSONObject.class);
// .map(i -> new Movie(i.get("backdrop_path").toString(), (List<Genre>) i.get("genre_ids"),Integer.parseInt(i.get("id").toString()),i.get("original_title").toString(),i.get("overview").toString(),i.get("poster_path").toString(),Float.parseFloat(i.get("vote_average").toString())))
//
//        List<Movie> movie = ((List<Object>)result.get("results"))
//                .stream()
//                .map(i -> (LinkedHashMap)i)
//                .map(i -> new Movie(i.get("backdrop_path").toString(), (List<Genre>) i.get("genre_ids"),Integer.parseInt(i.get("id").toString()),i.get("original_title").toString(),i.get("overview").toString(),i.get("poster_path").toString(),Float.parseFloat(i.get("vote_average").toString())))
//                        .toList();


//        return movie;
 //   }



//    @GetMapping(value="/clientHello3")
//     public Movie getMovieByGenreId()
//    {
//            String url = "https://api.themoviedb.org/3/movie/popular?api_key=dd4d819639705d332d531217b4f7c6b6&page=1&language=en-US&region=US";
//            RestTemplate restTemplate = new RestTemplate();
//            Movie movies = restTemplate.getForObject(url , Movie.class);
//
//        System.out.println(movies);
//
//        return new Movie(movies.getGenreList(),movies.getOriginal_title(),movies.getOverview());
//    }





//    @GetMapping(value = "/clientHello3")
//    private List<Movie> getMovieDetailsByGenre() {
//
//
//
//        String uri = "https://api.themoviedb.org/3/movie/popular?api_key=dd4d819639705d332d531217b4f7c6b6&page=1&language=en-US&region=US";
//        RestTemplate restTemplate = new RestTemplate();
//        JSONObject result = restTemplate.getForObject(uri, JSONObject.class);



//        List<Movie> movie = ((List<Object>)result.get("results"))
//                .stream()
//                .map(i -> (LinkedHashMap)i)
//                .map(i -> new Movie(i.get("backdrop_path").toString(), (int[]) i.get("genre_ids"), Integer.parseInt(i.get("id").toString()),i.get("original_title").toString(),i.get("overview").toString(),i.get("poster_path").toString(),Float.parseFloat(i.get("vote_average").toString())))
//               // .map(i -> new Movie(i.get("backdrop_path").toString(), (List<Genre>) i.get("genre_ids"),Integer.parseInt(i.get("id").toString()),i.get("original_title").toString(),i.get("overview").toString(),i.get("poster_path").toString(),Float.parseFloat(i.get("vote_average").toString())))
//                .toList();

        // List<Movie> moviesGenreId=movie.stream().map(i -> i).toList();
      // List  <List<Integer>> movieIds = moviesGenreId.stream().map(i -> i.getGenreList()).
     //  System.out.println(moviesGenreId);
      // System.out.println(moviesGenreId.get(0).getGenreList());

//       if(moviesGenreId.get(0).getOriginal_title().equals("The Super Mario Bros. Movie"))
//       {
//           System.out.println("Success");
//       }

      //  System.out.println(moviesGenreId.get(0).getGenre_ids());
//        int id = 53;
//        for(int i=0 ; i<moviesGenreId.size() ; i++) {
//            System.out.println("moviesGenreId "+i);
//            System.out.println(moviesGenreId.get(i));
//
//
//            for (int j = 0; j < moviesGenreId; j++) {
//                System.out.println(moviesGenreId.get(i).get(j));
//
//
//
//                if(moviesGenreId.get(i).get(j) == id)
//                {
//                    System.out.println("correct "+i);
//                }
//
//
//
//            }
//
//        }

      //  System.out.println(moviesGenreId);





      //  return movie;
   // }

}
