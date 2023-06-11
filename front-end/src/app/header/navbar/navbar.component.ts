import { Component, EventEmitter, Output } from '@angular/core';
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
}
