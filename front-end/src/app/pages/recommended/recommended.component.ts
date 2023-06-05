import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/movie';
import { MovieApiServiceService } from 'src/app/service/movie-api-service.service';
@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {

  constructor(private movieService:MovieApiServiceService) { }
 bannerResult:any=[];
 recommendedMoviesResult:Movie[]=[];
 upcomingMoviesResult:Movie[]=[];
 actionMoviesResult:Movie[]=[];
 comedyMoviesResult:Movie[]=[];
 crimeMoviesResult:Movie[]=[];
 familyMoviesResult:Movie[]=[];

  ngOnInit(): void {
    // this.bannerData();
    this.recommendedMovie();
    this.upcomingMovies();
    this.actionMovies();
    this.comedyMovies();
    this.crimeMovies();
    this.familyMovies();

  }

  // bannerData(){
  //   this.movieService.bannerApiData().subscribe((result)=>{
  //     console.log(result,'bannerresult');
  //     this.bannerResult = result.results;
  //   });
  // }

  recommendedMovie(){
    this.movieService.recommendedMovies().subscribe((result)=>{
      console.log(result,'recommended');
      this.recommendedMoviesResult = result;
    });
  }
  upcomingMovies(){
    this.movieService.upComingMovies().subscribe((result)=>{
      console.log(result,'upcoming Movies');
      this.upcomingMoviesResult = result;
    });
  }
  actionMovies(){
    this.movieService.actionMovies().subscribe((result)=>{
      console.log(result,'action Movies');
      this.actionMoviesResult = result;
    });
  }
  comedyMovies(){
    this.movieService.comedyMovies().subscribe((result)=>{
      console.log(result,'comedy Movies');
      this.comedyMoviesResult = result;
    });
  }
  crimeMovies(){
    this.movieService.crimeMovies().subscribe((result)=>{
      console.log(result,'crime Movies');
      this.crimeMoviesResult = result;
    });
  }
  familyMovies(){
    this.movieService.familyMovies().subscribe((result)=>{
      console.log(result,'family Movies');
      this.familyMoviesResult = result;
    });
  }
}
