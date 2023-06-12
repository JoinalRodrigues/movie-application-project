package com.niit.recommended.controller;

import com.niit.recommended.domain.*;

import com.niit.recommended.dto.MessageDTO;
import com.niit.recommended.exception.InvalidCredentialsException;
import com.niit.recommended.exception.TokenExpiredException;
import com.niit.recommended.service.RecommendedForUser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    public CompletableFuture<ResponseEntity<List<Genre>>> movieListGenre()
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getGenreList(), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(), gets recommended movie list")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/popularMovie")
    public CompletableFuture<ResponseEntity<List<Movie>>> getRecommendedMovieList()
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getRecommendedList(), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(genreName), gets movie list by genre name")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/{name}")
    public CompletableFuture<ResponseEntity<List<Movie>>> movieListByGenreName(@PathVariable String name)
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getMovieListByGenreName(name), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(movieName), gets movie list by movieName")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/movie/{movieName}")
    public CompletableFuture<ResponseEntity<List<Movie>>> movieListByMovieName(@PathVariable String movieName)
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getMovieListByMovieName(movieName), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(searchName), returns list of movie matching search name")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/recommended/searchMovie/{movieName}" )  //Search Function by giving MovieName
    public CompletableFuture<ResponseEntity<List<Movie>>> searchMovieListByMovieName(@PathVariable String movieName)
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.searchMovieListByMovieName(movieName), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(movieName), gets cast of movie")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/cast/{name}")
    public CompletableFuture<ResponseEntity<List<Cast>>> getCastForMovies(@PathVariable String name)
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getCastForMovies(name), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(movieName), gets specific movie data")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/{movieName}")
    public CompletableFuture<ResponseEntity<Object>> getMovieInfoData(String movieName)
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getMovieInfoData(movieName), HttpStatus.OK));
    }


    @ApiResponse(description = "Get(), gets upcoming movies list")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/upcomingMovies")
    public CompletableFuture<ResponseEntity<List<Movie>>> upcomingMovieList()
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.upcomingMovieList(), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(movieName), gets movie trailer")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/trailer/{name}")
    public CompletableFuture<ResponseEntity<List<Trailer>>> getMovieTrailer(@PathVariable String name)
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getMovieTrailer(name), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(), gets movie list belonging to action genre")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/Action")
    public CompletableFuture<ResponseEntity<List<Movie>>> getMovieListActionGenre()
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getMovieListActionGenre(), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(), gets movie list belonging to comedy genre")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/Comedy")
    public CompletableFuture<ResponseEntity<List<Movie>>> getMovieListComedyGenre()
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getMovieListComedyGenre(), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(), gets movie list belonging to crime genre")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/Crime")
    public CompletableFuture<ResponseEntity<List<Movie>>> getMovieListCrimeGenre()
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getMovieListCrimeGenre(), HttpStatus.OK));
    }

    @ApiResponse(description = "Get(), gets movie list belonging to family genre")
    @TimeLimiter(name = "TimeoutIn5Seconds", fallbackMethod = "fallback")
    @CircuitBreaker(name = "WindowOf10", fallbackMethod = "fallback")
    @GetMapping(value = "/thirdParty/Family")
    public CompletableFuture<ResponseEntity<List<Movie>>> getMovieListFamilyGenre()
    {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(this.recommendedForUser.getMovieListFamilyGenre(), HttpStatus.OK));
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
