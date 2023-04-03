import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { IUserDetails } from '../model/user-details';
import { IMembershipType } from '../model/membership-type';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  public userDetails: IUserDetails [] = [];
  public membershipType: IMembershipType[] = [];
  public idArrays: Array<number> = [];
  public isClicked: boolean = false;

  constructor(
    protected gymApiService: GymApiService,
    protected router: Router,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {
    this.getUserDetails();
    this.getMembershipDetails();
    this.loadUserDetalsForm();
  }
  
  userDetailsForm = this.formBuilder.group({
    dateFrom: [],
    dateTo: [],
    membershipTypes: [],
    searchInput: []
  });

  loadUserDetalsForm() : void {
    this.userDetailsForm.patchValue({
      dateFrom: undefined,
      dateTo: undefined,
      membershipTypes: 'default',
      searchInput: ''
    })
  }

  getUserDetails(){
    this.gymApiService.retrieveUserDetails().subscribe(detail => this.userDetails = detail.reverse());
  }

  getMembershipDetails(){
    this.gymApiService.retrieveMembershipPlans().subscribe(detail => this.membershipType = detail);
  }

  filterClicked(){
    if(this.isClicked === false){
      this.isClicked = true;
    }
    else{
      this.isClicked = false;
    }
  }

  onSelect(userDetails: IUserDetails){
    this.router.navigate(['/user-details',userDetails.id]);
  }

  onSearch(){
    this.gymApiService.retrieveFilteredUserList(this.userDetailsForm.value).subscribe(data => {
      this.userDetails = data;
      this.userDetails.reverse();
      console.log('Success!', data)
    }, error => console.log('Error!', error))
  }

  onClearFilter(){
    location.reload();
  }

}
