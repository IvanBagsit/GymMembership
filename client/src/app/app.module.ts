import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GymApiService } from './gym-api.service';
import { MembershipDetailsComponent } from './membership-details/membership-details.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { FrontEndComponent } from './front-end/front-end.component';

@NgModule({
  declarations: [
    AppComponent,
    MembershipDetailsComponent,
    UserDetailsComponent,
    FrontEndComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [GymApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
