import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { Movie } from 'src/app/model/movie';
import { Trailer } from 'src/app/model/trailer';
import { Cast } from 'src/app/model/cast';
import { MovieTrailerService } from 'src/app/service/movie-trailer.service';
import { MovieFavouritesService } from 'src/app/service/movie-favourites.service';
import { NotificationService } from 'src/app/service/notification.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  movie: any;
  delmovie: Movie[] = [];

  movies !: Movie[];
  trailers: any;

  trailer!: Trailer;

  casts !: Cast[];


  constructor(private movieService: MovieTrailerService, private movieFavourites: MovieFavouritesService, public domSanitizer: DomSanitizer, private route: ActivatedRoute,
    private router: Router, public movieFavouritesService: MovieFavouritesService, private notificationService: NotificationService
  ) {
    // this.movie = new Movie();
  }


  ngOnInit(): void {

    let originalTitle = this.route.snapshot.paramMap.get('originalTitle');
    console.log(originalTitle);
    this.getMovie(originalTitle);
    this.getTrailor(originalTitle);

    originalTitle && this.movieService.getMovieCast(originalTitle).subscribe((result) => {
      console.log(result, 'getcast');
      this.casts = result;

    })


  }
  getMovie(originalTitle: any) {
    originalTitle && this.movieService.getMovie(originalTitle).subscribe((data) => {
      console.log(data, 'getMovieDetails');
      this.movies = data;
      this.movie = data[0];
    })
  }

  getTrailor(originalTitle: any) {

    originalTitle && this.movieService.getTrailorsId(originalTitle).subscribe((result) => {
      console.log(result, 'getTrailer');
      this.trailers = result;
      this.trailer = this.trailers[0];
    });
  }

  add() {
    this.movieFavouritesService.addFavouriteMovies(this.movie).subscribe((data: any) => {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Added Successfully!!!',
        color: '#dd3675',
        showConfirmButton: false,
        timer: 1500
      })
      setTimeout(function () {
        window.location.reload();
      }, 1500);

    })
  };

  delete(index: any) {
    this.movieFavourites.deleteFavouriteMovies(index).subscribe((result) => {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Removed Successfully!!!',
        color: '#dd3675',
        showConfirmButton: false,
        timer: 1500
      })
      setTimeout(function () {
        window.location.reload();
      }, 2500);
    })
  }

  addNotification() {
    console.log(this.movie);
    this.notificationService.addNotification(this.movie).subscribe((data: any) => {

      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Notification Added!!!',
        color: '#dd3675',
        showConfirmButton: false,
        timer: 1500
      })
    })

  }
}

