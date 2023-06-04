import { Component, OnInit } from '@angular/core';
import {FormGroup,FormControl} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/service/auth-service.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  login = new FormGroup({
    email:new FormControl(''),
    password:new FormControl('')
  })
  constructor(private router:Router,private authService:AuthServiceService) { }

  ngOnInit(): void {
  }
  signin(){
    console.warn(this.login.value);
    this.authService.loginUser(this.login.value).subscribe((result=>{
      sessionStorage.setItem('token', result.message.substring(6));
      console.log("login succesfully");
    }))
    // this.router.navigate(['/movie'])
  }
}
// sessionStorage.setItem('token', res.message.substring(6));
