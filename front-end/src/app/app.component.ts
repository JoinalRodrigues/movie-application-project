import { Component,HostListener, Input, ViewChild } from '@angular/core';
import { MatLegacyMenuTrigger as MatMenuTrigger } from '@angular/material/legacy-menu';
import { MovieFavouritesService } from './service/movie-favourites.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'movie-app';
 
  sideNavStatus:boolean = false;
  
  constructor(private moviefavourites:MovieFavouritesService){
    if(!this.moviefavourites.favourites){
      if(sessionStorage.getItem('token')){
        this.moviefavourites.getFavouriteMovies().subscribe(res => this.moviefavourites.favourites = res);
      }
    }
  }
}
