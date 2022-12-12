import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { IMembershipType } from '../model/membership-type';

@Component({
  selector: 'app-membership-details',
  templateUrl: './membership-details.component.html',
  styleUrls: ['./membership-details.component.css']
})
export class MembershipDetailsComponent implements OnInit {

  public membershipType: IMembershipType[] = [];

  constructor(protected gymApiService : GymApiService) { }

  ngOnInit(): void {
    this.getMembershipDetails();
  }

  getMembershipDetails(){
    this.gymApiService.retrieveMembershipPlans().subscribe(details => this.membershipType = details);
  }



}
