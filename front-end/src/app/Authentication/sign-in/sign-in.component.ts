import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { MovieFavouritesService } from 'src/app/service/movie-favourites.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  login = new FormGroup({
    email:new FormControl(''),
    password:new FormControl('')
  })
  constructor(private router:Router,private authService:AuthServiceService,private favService:MovieFavouritesService) { }

  ngOnInit(): void {
  }
  signin(){
    console.warn(this.login.value);
    this.authService.loginUser(this.login.value).subscribe((result=>{
      sessionStorage.setItem('token', result.message.substring(6));
      console.log("login succesfully");
      this.favService.getFavouriteMovies().subscribe(data=>
        this.favService.favourites=data
      )
      this.router.navigate(['/movie'])
    }))
  }

  // loginForm = new FormGroup({
  //   username:new FormControl(''),
  //   password:new FormControl('')
  // })
  // constructor(private router:Router,private authService:AuthServiceService) { }

  // ngOnInit(): void {
  // }
 

  // loginUser(){
  //   console.warn(this.loginForm.value);
  //   this.authService.loginUser(this.loginForm.value).subscribe((result=>{
  //     sessionStorage.setItem('token', result.message.substring(6));
  //     console.log("login succesfully");
  //     this.router.navigate(['/movie']);

  //   }))
  // }  
}
