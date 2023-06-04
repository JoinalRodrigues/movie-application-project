import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/movie';
import { MovieApiServiceService } from 'src/app/service/movie-api-service.service';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  constructor(private movieService:MovieApiServiceService) { }

  ngOnInit(): void {
    
  }
}
