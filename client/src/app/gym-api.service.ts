import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Head, Observable } from 'rxjs';
import { throws } from 'assert';
import { IMembershipType } from './model/membership-type';
import { IUserDetails, UserDetails } from './model/user-details';
import { Signup } from './model/signup';
import { catchError } from 'rxjs';
import { throwError } from 'rxjs';
import { ISearch } from './model/search';

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

  retrieveUserDetail(id: number): Observable<IUserDetails> {
    return this.http.get<IUserDetails>(this.LOCAL_API_PATH + '/users/details/' + id);
  }

  retrieveConfig(name: string): Observable<string> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'text/plain, */*',
        'Content-Type': 'application/json' // We send JSON
      }),
      responseType: 'text' as 'json' // We accept plain text as response
    };
    return this.http.get<string>(this.LOCAL_API_PATH + '/config/retrieve/' + name, httpOptions);
  }

  retrieveFilteredUserList(search: ISearch): Observable<IUserDetails[]> {
    return this.http.post<IUserDetails[]>(this.LOCAL_API_PATH + '/users/details/search', search);
  }

  signupNewMember(userDetails: UserDetails): Observable<UserDetails>{
    return this.http.post<UserDetails>(this.LOCAL_API_PATH + '/users/create', userDetails)
    .pipe(catchError(this.errorHandler));
  }

  updateMemberDetails(userDetails: UserDetails): Observable<UserDetails>{
    return this.http.put<UserDetails>(this.LOCAL_API_PATH + '/users/update', userDetails);
  }

  deleteMember(id: number): Observable<string>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'text/plain, */*'
      }),
      responseType: 'text' as 'json' // We accept plain text as response
    };
    return this.http.delete<string>(this.LOCAL_API_PATH + '/users/delete/' + id, httpOptions);
  }

  errorHandler(error: HttpErrorResponse){
    return throwError(error);
  }

}
