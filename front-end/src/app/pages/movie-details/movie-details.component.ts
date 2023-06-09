import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { Movie } from 'src/app/model/movie';
import { Trailer } from 'src/app/model/trailer';
import { Cast } from 'src/app/model/cast';
import { MovieTrailerService } from 'src/app/service/movie-trailer.service';
import { MovieFavouritesService } from 'src/app/service/movie-favourites.service';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  movie: any;

  movies !: Movie[];
  trailers: any;
  // trailers !: Trailer[];  

  // trailer: any;
  trailer!: Trailer;

  casts !: Cast[];


  constructor(private movieService: MovieTrailerService, public domSanitizer: DomSanitizer, private route: ActivatedRoute,
    private router: Router, private movieFavouritesService: MovieFavouritesService
  ) {
    this.movie = new Movie();
  }


  ngOnInit(): void {

    let originalTitle = this.route.snapshot.paramMap.get('originalTitle');
    console.log(originalTitle);
    this.getMovie(originalTitle);
    this.getTrailor(originalTitle);

    originalTitle && this.movieService.getMovieCast(originalTitle).subscribe((result) => {
console.log(result,'getcast');
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

  add(){
    this.movieFavouritesService.addFavouriteMovies(this.movie).subscribe((data:any)=>{
      this.router.navigate(['/user-favourite']);
    })};


}

