import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserDetailsComponent } from './user-details/user-details.component';
import { MembershipDetailsComponent } from './membership-details/membership-details.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FrontEndComponent } from './front-end/front-end.component';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';

const routes: Routes = [
  {path: 'home', component: HomepageComponent},
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
export const routingComponents = [UserDetailsComponent, MembershipDetailsComponent, PageNotFoundComponent, HomepageComponent];
