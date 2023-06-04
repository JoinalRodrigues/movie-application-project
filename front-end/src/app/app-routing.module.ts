import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SearchComponent } from './pages/search/search.component';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
import { SigninComponent } from './Authentication/signin/signin.component';
import { SignupComponent } from './Authentication/signup/signup.component';
import { RecommendedComponent } from './pages/recommended/recommended.component';
import { NotificationComponent } from './pages/notification/notification.component';
import { AdminUsersComponent } from './pages/admin-users/admin-users.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'search',component:SearchComponent},
  {path:'login',component:SigninComponent},
  {path:'signup',component:SignupComponent},
  {path:'movie',component:RecommendedComponent},
  {path:'movie',component:RecommendedComponent},
  {path:'notification',component:NotificationComponent},
  {path:'admin-user',component:AdminUsersComponent}

  // {path:'movie/:originalTitle',component:MovieDetailsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
