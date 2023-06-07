import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from '../movie';
import { Trailer } from '../trailer';
import { Cast } from '../cast';


@Injectable({
  providedIn: 'root'
})
export class MovieTrailerService {


   movieUrl:string;
   movieUrl2:string;
   movieTrailerUrl:string;
   castUrl:string;
  

  constructor(private http:HttpClient) { 
    this.movieUrl = "http://34.83.1.21/api/v1/recommended/popularMovie";
    this.movieUrl2 = "http://34.83.1.21/api/v1/recommended/searchMovie/";
    this.movieTrailerUrl = "http://34.83.1.21/api/v1/thirdParty/trailer/";
    this.castUrl = "http://34.83.1.21/api/v1/thirdParty/cast/"
  }


  baseUrl = 'https://api.themoviedb.org/3/';
  apiKey = "08cc33bd5ae3a747598ce2ad84376e66";

  // bannerApiData():Observable<any>{
  //   return this.http.get(`${this.baseUrl}/trending/all/week?api_key=${this.apiKey}`);
  // }
  // recommendedMovies():Observable<Movie[]>{
  //   return this.http.get<Movie[]>(this.movieUrl);
  // }


  getMovie(movieName:String):Observable<Movie[]>{
    return this.http.get<Movie[]>(this.movieUrl2+movieName);
  }

  // public getTrailorsId(movieName:String):Observable<Trailer[]>{
  //   return this.http.get<Trailer[]>(this.movieTrailerUrl+movieName);
  // }

  public getMovieCast(movieName:string):Observable<Cast[]>{
    return this.http.get<Cast[]>(this.castUrl+movieName);
  }


  
  public getTrailorsId(movieName: string): Observable<any> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<any>(`${this.movieTrailerUrl}/${movieName}`,httpOptions);
  }

}
