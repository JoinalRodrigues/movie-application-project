import { Component, EventEmitter, HostListener, Output, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { AuthServiceService } from 'src/app/service/auth-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent {
  @Output() sideNavToggled = new EventEmitter<boolean>();
  menuStatus:boolean = true;

  constructor(private auth:AuthServiceService){
    this.window = window;
  }

  window:Window;
  SideNavToggle(){
this.menuStatus = !this.menuStatus;
this.sideNavToggled.emit(this.menuStatus);
  }
  logout() {
    this.auth.loggingOut();
    
}
navbg:any;
  @HostListener('document:scroll') scrollover(){
if(document.body.scrollTop > 0 || document.documentElement.scrollTop > 0){
  this.navbg = {
    'background-color': '#000000a8'

    // 'background-color':'#ffffff'
    // 'background-color':'hsl(218, 41%, 30%)',
         
  }
}else{
  this.navbg = {}
}
  }
  @ViewChild(MatMenuTrigger) trigger!: MatMenuTrigger;

  someMethod() {
    this.trigger.openMenu();
  }
}
