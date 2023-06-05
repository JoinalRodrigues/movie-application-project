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

  public recommendedMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/recommended/popularMovie', httpOptions);
  }


  public searchMovie(movieName: string): Observable<any> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<any>(`${this.baseUrl + '/api/v1/recommended/searchMovie'}/${movieName}`, httpOptions);
  }


  upComingMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/upcomingMovies', httpOptions);
  }

  actionMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Action', httpOptions);
  }
  comedyMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Comedy', httpOptions);
  }
  crimeMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Crime', httpOptions);
  }
  familyMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + '/api/v1/thirdParty/Family', httpOptions);
  }
}
