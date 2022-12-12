import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { IUserDetails } from '../model/user-details';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  public userDetails: IUserDetails [] = [];

  constructor(protected gymApiService: GymApiService) { }

  ngOnInit(): void {
    this.getUserDetails();
  }

  getUserDetails(){
    this.gymApiService.retrieveUserDetails().subscribe(detail => this.userDetails = detail);
  }

}
