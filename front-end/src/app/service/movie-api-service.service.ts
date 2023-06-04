import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../movie';

@Injectable({
  providedIn: 'root'
})
export class MovieApiServiceService {


  private movieUrl!: string;
  private movieUrl2!: string;
  private movieUrl3!: string;

  private baseUrl!: string



  constructor(private http: HttpClient) {
    this.movieUrl = "http://localhost:8081/api/v1/recommended/popularMovie";
    this.movieUrl2 = "http://localhost:8081/api/v1/recommended/searchMovie";
    this.movieUrl3 = "http://localhost:8081/api/v1/thirdParty/upcomingMovies";
    this.baseUrl = "http://localhost:8081/api/v1/thirdParty";

  }
  // baseUrl = 'https://api.themoviedb.org/3/';
  // apiKey = "08cc33bd5ae3a747598ce2ad84376e66";



  // bannerApiData():Observable<any>{
  //   return this.http.get(`${this.baseUrl}/trending/all/week?api_key=${this.apiKey}`);
  // }

  recommendedMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.movieUrl);
  }

  searchMovie(movieName: string): Observable<any> {
    return this.http.get<any>(`${this.movieUrl2}/${movieName}`);
  }

  upComingMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.movieUrl3);

  }
  actionMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.baseUrl}/Action`);
  }
  comedyMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.baseUrl}/Comedy`);
  }
  crimeMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.baseUrl}/Crime`);
  }
  familyMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.baseUrl}/Family`);
  }
}
