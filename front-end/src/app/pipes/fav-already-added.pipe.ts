import { Pipe, PipeTransform } from '@angular/core';
import { Role } from '../service/admin.service';
import { Movie } from '../model/movie';
import { MovieFavouritesService } from '../service/movie-favourites.service';
import { Observable } from 'rxjs';

@Pipe({
  name: 'favAlreadyAdded'
})
export class FavAlreadyAddedPipe implements PipeTransform {
  constructor(private favService: MovieFavouritesService) {
  }
  transform(value: number, ...args: unknown[]): boolean {
    if (!this.favService.favourites)
      return false;
    return (args[0] as boolean) ? this.favService.favourites?.filter(i => i.movieId == value).length > 0 : this.favService.favourites?.filter(i => i.movieId == value).length == 0;
  }



}


