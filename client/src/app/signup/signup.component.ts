import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { Signup } from '../model/signup';
import { UserDetails } from '../model/user-details';
import { IMembershipType, MembershipType } from '../model/membership-type';
import { AccountType, IAccountType } from '../model/account-type';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public title : string = "";
  public formValidation : boolean = false;
  public membershipType: IMembershipType[] = [];

  signupModel: UserDetails = new UserDetails(0,"",undefined,"","",0,new Date,new Date,new Date,
  new Date,new Date,false,false, new MembershipType(1,"Daily",50,"1"), new AccountType(2,"user")
  );

  constructor(protected gymApiService: GymApiService) { }

  ngOnInit(): void {
    this.retrieveConfig('title');
    this.getMembershipDetails();
  }
  
  retrieveConfig(name: string){
    this.gymApiService.retrieveConfig(name).subscribe(config => this.title = config);
  }

  onClick(formValue: boolean){
    formValue === true ? this.formValidation = true : this.formValidation = false;
  }

  onSubmit(){
    console.log('MODEL', this.signupModel);
    this.gymApiService.signupNewMember(this.signupModel).subscribe(
      data => console.log('Success!', data),
      error => console.log('Error!', error)
    );
  }

  getMembershipDetails(){
    this.gymApiService.retrieveMembershipPlans().subscribe(details => this.membershipType = details);
  }
}
