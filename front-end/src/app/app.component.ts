import { Component,HostListener, Input, ViewChild } from '@angular/core';
import { MatLegacyMenuTrigger as MatMenuTrigger } from '@angular/material/legacy-menu';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'movie-app';
  navbg:any;
  sideNavStatus:boolean = false;
  
  @HostListener('document:scroll') scrollover(){
if(document.body.scrollTop > 0 || document.documentElement.scrollTop > 0){
  this.navbg = {
    // 'background-color':'hsl(218, 41%, 30%)',

    // 'background-color':'#ffffff'
    // 'background-color':'hsl(218, 41%, 30%)',
         
  }
}else{
  this.navbg = {

  }
}
  }
  @ViewChild(MatMenuTrigger) trigger!: MatMenuTrigger;

  someMethod() {
    this.trigger.openMenu();
  }
}
