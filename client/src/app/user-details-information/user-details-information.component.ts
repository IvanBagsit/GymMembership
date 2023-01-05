import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';
import { IUserDetails } from '../model/user-details';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

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
    private router: Router
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

  goToUserDetails() {
    let selectedId = this.userId ? this.userId : null;
    // this.router.navigate(['/user-details', {id: selectedId}]);
    this.router.navigate(['../', {id: selectedId}], {relativeTo: this.route});
  }

  showConfirm() {
    this.router.navigate(['confirm'], {relativeTo: this.route});
  }

}
