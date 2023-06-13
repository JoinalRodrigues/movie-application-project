import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from '../model/movie';
import { Trailer } from '../model/trailer';
import { Cast } from '../model/cast';


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



  getMovie(movieName:String):Observable<Movie[]>{
    return this.http.get<Movie[]>(this.movieUrl2+movieName);
  }


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
