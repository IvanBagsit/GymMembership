import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public title : string = "";

  constructor(protected gymApiService: GymApiService) { }

  ngOnInit(): void {
    this.retrieveConfig('title');
  }
  
  retrieveConfig(name: string){
    this.gymApiService.retrieveConfig(name).subscribe(config => this.title = config);
  }
}
