import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { GymApiService } from './gym-api.service';
import { MembershipDetailsComponent } from './membership-details/membership-details.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { FrontEndComponent } from './front-end/front-end.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { SettingsComponent } from './settings/settings.component';
import { TermsAndConditionComponent } from './terms-and-condition/terms-and-condition.component';
import { UserDetailsInformationComponent } from './user-details-information/user-details-information.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    FrontEndComponent,
    PageNotFoundComponent,
    HomepageComponent,
    LoginComponent,
    SignupComponent,
    SettingsComponent,
    TermsAndConditionComponent,
    UserDetailsInformationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [GymApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
