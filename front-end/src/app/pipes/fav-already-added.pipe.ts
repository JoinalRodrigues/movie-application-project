import { Pipe, PipeTransform } from '@angular/core';
import { Role } from '../service/admin.service';
import { Movie } from '../model/movie';
import { MovieFavouritesService } from '../service/movie-favourites.service';
import { Observable } from 'rxjs';

@Pipe({
  name: 'favAlreadyAdded'
})
export class FavAlreadyAddedPipe implements PipeTransform {
constructor(private favService:MovieFavouritesService){

}

  transform(value: number, ...args: unknown[]): Observable<boolean> {
    // console.log(value,'value');
    // console.log(args[0]);
  return new Observable<boolean>(observor => {
    observor.next((args[0] as boolean) ? this.favService.favourites.filter(i=>i.movieId===value).length>0 : this.favService.favourites.filter(i=>i.movieId===value).length == 0);
    observor.complete();
  });
  //(args[0] as Movie[]).filter(i=>i.movieId===value).length>0;

  
  }

}
