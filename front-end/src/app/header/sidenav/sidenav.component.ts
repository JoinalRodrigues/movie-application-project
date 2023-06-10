import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent{

  @Input() sideNavStatus:boolean = false;
  
list=[
 
  {
number:'1',
name:'home',
icon:'home',
routeLink:''

  },
  {
    number:'1',
    name:'search',
    icon:'search',
    routeLink:'search'
      },
      {
        number:'1',
        name:'Movie',
        icon:'movie',
        routeLink:'movie'

          },
          {
            number:'1',
            name:'Favourites',
            icon:'favorite',
            routeLink:'user-favourite'
    
              },
          {
            number:'1',
            name:'Logout',
            icon:'logout',
            // routeLink:'movie'
            
              }
             
]
}
