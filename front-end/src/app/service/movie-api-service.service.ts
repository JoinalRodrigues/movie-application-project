import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../model/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieApiServiceService {


  private baseUrl: string = 'http://34.83.1.21';


  constructor(private http: HttpClient) {

  }

  public recommendedMovies(): Observable<Movie[]> {

    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/recommended/popularMovie');
  }

  public searchMovie(movieName: string): Observable<any> {
   
    return this.http.get<any>(`${this.baseUrl + '/api/v1/recommended/searchMovie'}/${movieName}`);
  }


  upComingMovies(): Observable<Movie[]> {
   
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/upcomingMovies');
  }

  actionMovies(): Observable<Movie[]> {
  
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Action');
  }
  comedyMovies(): Observable<Movie[]> {
   
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Comedy');
  }
  crimeMovies(): Observable<Movie[]> {
  
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
