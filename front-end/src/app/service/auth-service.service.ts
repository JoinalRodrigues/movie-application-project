import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { Route, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private usersUrl1: string;
  private usersUrl2: string;

  constructor(private http: HttpClient,private router:Router) {
    this.usersUrl2='http://34.83.1.21/api/v1/login';
    this.usersUrl1= 'http://34.83.1.21/api/v1/register'
    
    }
   
    public registerUser(user:FormData):Observable<any>{
      return this.http.post<User>(this.usersUrl1, user);
    }

    public loginUser(user:any):Observable<any>{
      return  this.http.post<User>(this.usersUrl2, user);
    }
    // public loginUser(user:FormData):Observable<any>{
    //   return  this.http.post<User>(this.usersUrl2, user);
    // }
      loggingOut(){
        sessionStorage.clear();
        this.router.navigateByUrl("");
      }
    
}
