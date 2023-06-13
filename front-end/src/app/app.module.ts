import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SearchComponent} from './pages/search/search.component';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
import  {HttpClientModule } from '@angular/common/http';
import { MovieApiServiceService } from './service/movie-api-service.service';
import {FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatChipsModule } from '@angular/material/chips';
import {MatTabsModule} from '@angular/material/tabs';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatListModule } from '@angular/material/list';
import { RecommendedComponent } from './pages/recommended/recommended.component';
import { MatTableModule } from '@angular/material/table';
import {MatCheckboxModule } from '@angular/material/checkbox';
import { MatSelectModule } from '@angular/material/select';
import { MatPaginatorModule } from '@angular/material/paginator';
import { NotificationComponent } from './pages/notification/notification.component';
import { ArrayCombineSubstringPipe } from './pipes/array-combine-substring.pipe';
import {ImageAuthPipe } from './pipes/image-auth-pipe';
import { FavouriteMoviesComponent } from './pages/favourite-movies/favourite-movies.component';
import {MatCardModule} from '@angular/material/card';
import { AdminUsersComponent } from './pages/admin-users/admin-users.component';
import { SidenavComponent } from './header/sidenav/sidenav.component';
import { NavbarComponent } from './header/navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { SignInComponent } from './Authentication/sign-in/sign-in.component';
import { SignUpComponent } from './Authentication/sign-up/sign-up.component';
import { FavAlreadyAddedPipe } from './pipes/fav-already-added.pipe';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchComponent,
    MovieDetailsComponent,
    RecommendedComponent,
    NotificationComponent,
    AdminUsersComponent,
    ArrayCombineSubstringPipe,
    FavouriteMoviesComponent,
    ImageAuthPipe,
    FavAlreadyAddedPipe,
    SidenavComponent,
    NavbarComponent,
    FooterComponent,
    SignInComponent,
    SignUpComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatIconModule,MatToolbarModule,MatMenuModule,MatButtonModule,MatChipsModule,MatListModule,MatGridListModule,
    MatTableModule, MatCheckboxModule, MatSelectModule, MatPaginatorModule,MatCardModule,MatTabsModule
  ],
  providers: [MovieApiServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
