import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../movie';

@Injectable({
  providedIn: 'root'
})
export class MovieApiServiceService {


  private baseUrl: string = 'http://34.83.1.21';


  constructor(private http: HttpClient) {

  }
   baseUrl1 = 'https://api.themoviedb.org/3/';
  apiKey = "08cc33bd5ae3a747598ce2ad84376e66";



  bannerApiData():Observable<any>{
    return this.http.get(`${this.baseUrl1}/trending/all/week?api_key=${this.apiKey}`);
  }

  public recommendedMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/recommended/popularMovie');
  }


  public searchMovie(movieName: string): Observable<any> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<any>(`${this.baseUrl + '/api/v1/recommended/searchMovie'}/${movieName}`);
  }


  upComingMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/upcomingMovies');
  }

  actionMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Action');
  }
  comedyMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Comedy');
  }
  crimeMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Crime');
  }
  familyMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Family');
  }
}
