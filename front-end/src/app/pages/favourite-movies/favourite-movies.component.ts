import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { MovieTrailerService } from 'src/app/service/movie-trailer.service';
import { Movie } from 'src/app/movie';
import { MovieFavouritesService } from 'src/app/service/movie-favourites.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-favourite-movies',
  templateUrl: './favourite-movies.component.html',
  styleUrls: ['./favourite-movies.component.css']
})
export class FavouriteMoviesComponent implements OnInit {


  movie: Movie[] = [];
  movies !: Movie[];

  constructor(private movieService: MovieTrailerService, private movieFavourites: MovieFavouritesService, public domSanitizer: DomSanitizer, private route: ActivatedRoute,
    private router: Router) {
    // this.movie = new Movie();
  }

  ngOnInit(): void {
    this.movieFavourites.getFavouriteMovies().subscribe(data => {
      this.movie = data;
    });
  }

  deleteFavouriteMovie(index: any) {
    this.movieFavourites.deleteFavouriteMovies(index.movieId).subscribe((result) => {     
      window.location.reload();
    })
  }
}
