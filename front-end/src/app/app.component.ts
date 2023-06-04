import { Component,HostListener, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'movie-app';
  navbg:any;
  @HostListener('document:scroll') scrollover(){
if(document.body.scrollTop > 0 || document.documentElement.scrollTop > 0){
  this.navbg = {
    // 'background-color':'#ffffff'
    // 'background-color':'hsl(218, 41%, 30%)',
         
  }
}else{
  this.navbg = {
    'background-color':'hsl(218, 41%, 30%)',

  }
}
  }
  @ViewChild(MatMenuTrigger) trigger!: MatMenuTrigger;

  someMethod() {
    this.trigger.openMenu();
  }
}
