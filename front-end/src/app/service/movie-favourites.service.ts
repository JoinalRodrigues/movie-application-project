import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../model/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieFavouritesService {

  sendFavouritesUrl!: string;
  getFavouriteUrl !: string;
  deleteFavouriteUrl !: string;

  public favourites: Movie[] | null = null;

  constructor(private http: HttpClient) {

    this.sendFavouritesUrl = 'api/v1/user/favourite';
    this.getFavouriteUrl = 'api/v1/user/favourite';
    this.deleteFavouriteUrl = 'api/v1/user/favourite/';
  }


  baseUrl = 'http://34.83.1.21/'


  public getFavouriteMovies(): Observable<Movie[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }
    return this.http.get<Movie[]>(this.baseUrl + this.getFavouriteUrl, httpOptions);
  }



  addFavouriteMovies(movie: Movie) {
    console.log(movie, 'movie');
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }

    return this.http.post<Movie>(this.baseUrl + this.sendFavouritesUrl, movie, httpOptions);

  }


  deleteFavouriteMovies(movieId: number) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    }

    return this.http.delete<Movie>(this.baseUrl + this.deleteFavouriteUrl + movieId, httpOptions);

  }




}
