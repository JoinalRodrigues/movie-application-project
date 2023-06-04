import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private baseUrl: string = 'http://34.83.1.21';

  constructor(private http: HttpClient) {
    }
  
    public getNotifications():Observable<Notification>{
      let httpOptions = {
        headers: new HttpHeaders({
          'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        })
      }
      return this.http.get<Notification>(this.baseUrl + '/api/v1/user/notifications', httpOptions);
    }

    public addNotification():Observable<any>{
      let httpOptions = {
        headers: new HttpHeaders({
          'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        })
      }
      return this.http.get<Notification>(this.baseUrl + '/api/v1/user/notifications', httpOptions);
    }

}

export type Notification = {
  position: number;
  movieName: string;
  releaseDate: string;
}