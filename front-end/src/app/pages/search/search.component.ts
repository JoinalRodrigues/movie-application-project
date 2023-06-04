import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl,FormGroup } from '@angular/forms';
import { Movie } from 'src/app/movie';
import { MovieApiServiceService } from 'src/app/service/movie-api-service.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  // movies:Movie[]=[];
  constructor(private service:MovieApiServiceService) { }

  ngOnInit(): void {
  }

  searchResult:Movie[]=[];

  searchStr: string="";


  search() {
    this.service.searchMovie(this.searchStr).subscribe(result => {
      console.log(result,'searchMovies');
      this.searchResult = result;
    });
  }
  }
 

