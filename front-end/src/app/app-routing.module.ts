import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SearchComponent } from './pages/search/search.component';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';

import { RecommendedComponent } from './pages/recommended/recommended.component';
import { NotificationComponent } from './pages/notification/notification.component';
import { FavouriteMoviesComponent } from './pages/favourite-movies/favourite-movies.component';
import { AdminGuard } from './guard/admin.guard';
import { AdminUsersComponent } from './pages/admin-users/admin-users.component';
import { SignUpComponent } from './Authentication/sign-up/sign-up.component';
import { SignInComponent } from './Authentication/sign-in/sign-in.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'search',component:SearchComponent},
  {path:'movie',component:RecommendedComponent},
  {path:'notification',component:NotificationComponent},
  {path:'admin-user',component:AdminUsersComponent,canActivate:[AdminGuard]},
  {path:'movie/:originalTitle',component:MovieDetailsComponent},
  {path:'user-favourite' , component:FavouriteMoviesComponent},
  {path:'signup', component:SignUpComponent},
  {path:'signin', component:SignInComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
