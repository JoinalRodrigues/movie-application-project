package com.niit.movieservice.controller;

import com.niit.movieservice.dto.NotificationDTO;
import com.niit.movieservice.exception.*;
import com.niit.movieservice.model.Movie;
import com.niit.movieservice.model.User;
import com.niit.movieservice.service.UserMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserMovieController {
    @Autowired
   UserMovieService userMovieService;
    private ResponseEntity<?> responseEntity;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("file") MultipartFile file) throws UserAlreadyExistsException , InternalServerError {
        User user = new User();
        try {
            responseEntity =  new ResponseEntity<>(userMovieService.registerUser(email,password,file), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException | IOException | ExecutionException | InterruptedException e)
        {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }
    @PostMapping("/user/notification")
    public ResponseEntity<?> addNotification(@RequestBody Movie movie,Principal principal) throws Exception, UserNotFoundException {
        return new ResponseEntity<>(userMovieService.pushNotification(movie, principal.getName()), HttpStatus.OK);

    }
    @GetMapping("/user/notification")
    public ResponseEntity<?> getNotification(Principal principal){
        return new ResponseEntity<>(userMovieService.getNotification(principal.getName()), HttpStatus.OK);

    }
    @PostMapping("/user/favourite")
    public ResponseEntity<?> addMovieToFavourites(@RequestBody Movie movie, Principal principal) throws UserNotFoundException{

        return new ResponseEntity<>(userMovieService.addMovieToFavourites(movie, principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/user/favourite")
    public ResponseEntity<?> getAllFavourites(Principal principal) {
        return new ResponseEntity<>(userMovieService.getAllFavouriteMovies(principal.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/user/favourite/{movieId}")
    public ResponseEntity<?> deleteFavouriteFromList(@PathVariable int movieId,Principal principal) throws MovieNotFoundException
    {
        return new ResponseEntity<>(userMovieService.removeMovieFromFavourites(movieId,principal.getName()), HttpStatus.OK);

    }
}
