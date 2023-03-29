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
  public errorMsg: string = "";

  signupModel: UserDetails = new UserDetails(0,"",undefined,"","",0,new Date,new Date,new Date,
  new Date,new Date,false,false, undefined, new AccountType(2,"user")
  );

  constructor(protected gymApiService: GymApiService) { }

  ngOnInit(): void {
    this.retrieveConfig('title');
    this.getMembershipDetails();
  }
  
  retrieveConfig(name: string){
    this.gymApiService.retrieveConfig(name).subscribe(config => this.title = config);
  }

  onSubmit(formValue: boolean){
    
    formValue === true ? this.formValidation = true : this.formValidation = false;

    if(this.formValidation === false){
      console.log('MODEL', this.signupModel);
      this.gymApiService.signupNewMember(this.signupModel).subscribe(
        data => {
          console.log('Succes!', data),
          location.reload()
      },
        error => this.errorMsg = error.error.toString()
      );
    }
  }

  getMembershipDetails(){
    this.gymApiService.retrieveMembershipPlans().subscribe(details => this.membershipType = details);
  }
}
