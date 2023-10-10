import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { IUserDetails } from '../model/user-details';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { IMembershipType } from '../model/membership-type';

@Component({
  selector: 'app-user-details-information',
  templateUrl: './user-details-information.component.html',
  styleUrls: ['./user-details-information.component.css']
})
export class UserDetailsInformationComponent implements OnInit {

  public userDetails !: IUserDetails;
  public userId !: any;
  public booleanOptions : string[] = ['true', 'false'];
  public membershipType: IMembershipType[] = [];
  public membershipTypes: any[] = [];

  constructor(
    protected gymApiService: GymApiService,
    private route: ActivatedRoute,
    protected router: Router,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {
    let id = this.route.paramMap.subscribe((params : ParamMap) => {
      let id = params.get('id');
      this.userId = id;
    });
    this.retrieveUserDetail(this.userId);
    this.getMembershipDetails();

    // conditional validation
    this.userDetailsInformationForm.get('age')?.valueChanges
      .subscribe(checkedValue => {
        const username = this.userDetailsInformationForm.get('username');
        if(checkedValue){
          username?.clearValidators();
        }
        username?.updateValueAndValidity();
      })
  }

  get username() {
    return this.userDetailsInformationForm.get('username');
  }

  userDetailsInformationForm = this.formBuilder.group({
    id: [],
    firstName: [],
    lastName: [],
    birthday: [],
    age: [],
    expirationDate: [],
    joinDate: [],
    termsAndCondition: [],
    disable: [],
    membershipType: this.formBuilder.group({
      id: [],
      type: [],
      fee: [],
      duration: []
    })
  })

  retrieveUserDetail(id: number): void{
    this.gymApiService.retrieveUserDetail(id).subscribe(data => {
      this.userDetails = data;
      this.loadUserData();
    });
  }

  loadUserData(): void{
    this.userDetailsInformationForm.patchValue({
      id: this.userId,
      firstName: this.userDetails.firstName,
      lastName: this.userDetails.lastName,
      birthday: this.userDetails.birthday,
      age: this.userDetails.age,
      expirationDate: this.userDetails.expirationDate,
      joinDate: this.userDetails.joinDate,
      termsAndCondition: this.userDetails.termsAndCondition,
      disable: this.userDetails.disable,
      membershipType: {
        id: this.userDetails.membershipType?.id,
        type: this.userDetails.membershipType.type,
        fee: this.userDetails.membershipType.fee,
        duration: this.userDetails.membershipType.duration
      }
    });
  }

  getMembershipDetails(){
    this.gymApiService.retrieveMembershipPlans().subscribe(details => this.membershipType = details);
    this.membershipType.forEach(data => {
      this.membershipTypes.push(data.type);
    });
  }

  onSubmit() {
    console.log('USER DETAILS UPDATE FORM',this.userDetailsInformationForm.value);
    this.gymApiService.updateMemberDetails(this.userDetailsInformationForm.value).subscribe(
      data => {
        console.log('Success!', data);
        location.reload();
      },
      error => console.log('Error!', error)
    );
  }

  onDelete() {
    this.gymApiService.deleteMember(this.userId).subscribe(data => {
      console.log('Success!', data);
    },
    error => console.log('Error!', error));
    this.router.navigate(['/home']);
  }

}
