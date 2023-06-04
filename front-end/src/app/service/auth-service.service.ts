import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private usersUrl1: string;
  private usersUrl2: string;

  constructor(private http: HttpClient) {
    this.usersUrl2='http://34.83.1.21/api/v1/login';
    this.usersUrl1= 'http://34.83.1.21/api/v1/register'
    
    }
   
    public registerUser(user:FormData):Observable<any>{
      return this.http.post<User>(this.usersUrl1, user);
    }

    public loginUser(user:FormData):Observable<any>{
      return  this.http.post<User>(this.usersUrl2, user);
    }

}
