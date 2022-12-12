import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { throws } from 'assert';
import { IMembershipType } from './model/membership-type';
import { IUserDetails } from './model/user-details';

@Injectable({
  providedIn: 'root'
})
export class GymApiService {

  // CORS POLICY = 'http://' should be added in the url
  private LOCAL_API_PATH = 'http://localhost:8080/gymmembership';

  constructor(private http: HttpClient) { }

  retrieveMembershipPlans(): Observable<IMembershipType[]> {
    return this.http.get<IMembershipType[]>(this.LOCAL_API_PATH + '/membership/details');
  }

  retrieveUserDetails(): Observable<IUserDetails[]>{
    return this.http.get<IUserDetails[]>(this.LOCAL_API_PATH + '/users/details');
  }

}
