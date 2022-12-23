import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserDetailsComponent } from './user-details/user-details.component';
import { MembershipDetailsComponent } from './membership-details/membership-details.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FrontEndComponent } from './front-end/front-end.component';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { SettingsComponent } from './settings/settings.component';
import { DataComponent } from './data/data.component';
import { TermsAndConditionComponent } from './terms-and-condition/terms-and-condition.component';

const routes: Routes = [
  {path: 'home', component: HomepageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'settings', component: SettingsComponent},
  {path: 'data', component: DataComponent},
  {path: 'terms-and-condition', component: TermsAndConditionComponent},
  {path: 'user-details', component: UserDetailsComponent},
  {path: 'membership-details', component: MembershipDetailsComponent},
  {path: '', redirectTo:'/home', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [
  UserDetailsComponent, 
  MembershipDetailsComponent, 
  PageNotFoundComponent, 
  HomepageComponent,
  LoginComponent,
  SignupComponent,
  SettingsComponent,
  TermsAndConditionComponent
];
