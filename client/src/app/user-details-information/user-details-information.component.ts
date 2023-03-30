import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { IUserDetails } from '../model/user-details';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-details-information',
  templateUrl: './user-details-information.component.html',
  styleUrls: ['./user-details-information.component.css']
})
export class UserDetailsInformationComponent implements OnInit {

  public userDetails !: IUserDetails;
  public userId !: any;

  constructor(
    protected gymApiService: GymApiService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {
    let id = this.route.paramMap.subscribe((params : ParamMap) => {
      let id = params.get('id');
      this.userId = id;
    });
    this.retrieveUserDetail(this.userId);
  }

  get username() {
    return this.userDetailsInformationForm.get('username');
  }

  userDetailsInformationForm = this.formBuilder.group({
    username: ['',[Validators.required,Validators.minLength(1)]],
    password: ['',Validators.required],
    birthday: [],
    age: [],
    lastLogIn: [],
    lastLogOut: [],
    expirationDate: [],
    joinDate: [],
    termsAndCondition: [],
    disable: [],
    membershipType: this.formBuilder.group({
      id: [],
      type: [],
      fee: [],
      duration: []
    }),
    accountType: this.formBuilder.group({
      id: [],
      role: []
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
      username: this.userDetails.username,
      password: this.userDetails.password,
      birthday: this.userDetails.birthday,
      age: this.userDetails.age,
      lastLogIn: this.userDetails.lastLogIn,
      lastLogOut: this.userDetails.lastLogOut,
      expirationDate: this.userDetails.expirationDate,
      joinDate: this.userDetails.joinDate,
      termsAndCondition: this.userDetails.termsAndCondition,
      disable: this.userDetails.disable,
      membershipType: {
        id: this.userDetails.membershipType?.id,
        type: this.userDetails.membershipType.type,
        fee: this.userDetails.membershipType.fee,
        duration: this.userDetails.membershipType.duration
      },
      accountType:{
        id: this.userDetails.accountType.id,
        role: this.userDetails.accountType.role
      }
    })
  }

}
