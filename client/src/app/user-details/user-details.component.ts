import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { IUserDetails } from '../model/user-details';
import { IMembershipType } from '../model/membership-type';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';


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
  public selectedId: any;

  constructor(
    protected gymApiService: GymApiService,
    protected router: Router,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.getUserDetails();
    this.getMembershipDetails();
    let id = this.route.paramMap.subscribe((params : ParamMap) => {
      let id = params.get('id');
      this.selectedId = id;
    });
  }

  getUserDetails(){
    this.gymApiService.retrieveUserDetails().subscribe(detail => this.userDetails = detail);
  }

  getMembershipDetails(){
    this.gymApiService.retrieveMembershipPlans().subscribe(details => this.membershipType = details);
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
    // this.router.navigate([userDetails.id], {relativeTo: this.route});
  }

  isSelected(userDetails: IUserDetails){
    return userDetails.id === parseInt(this.selectedId);
  }

}
