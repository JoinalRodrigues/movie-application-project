import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable, map } from 'rxjs';

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
      let position = 1;
      return this.http.get<any>(this.baseUrl + '/api/v1/user/notifications', httpOptions).
      pipe(map(i=>{
        let notification = new Notification();
        notification.position = position;
        position++;
        notification.movieName = i?.movie?.movieName;
        notification.releaseDate = i?.movie?.releaseDate;
        return notification;
      }));
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

export class Notification  {
  position: number=0;
  movieName: string="";
  releaseDate: string="";
}