import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { IUserDetails } from '../model/user-details';
import { ActivatedRoute, ParamMap } from '@angular/router';

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
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    let id = this.route.paramMap.subscribe((params : ParamMap) => {
      let id = params.get('id');
      this.userId = id;
    });
    this.retrieveUserDetail(this.userId);
  }

  retrieveUserDetail(id: number){
    this.gymApiService.retrieveUserDetail(id).subscribe(data => {
      this.userDetails = data;
    })
  }

}
