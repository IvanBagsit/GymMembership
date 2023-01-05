import { Component, OnInit } from '@angular/core';
import { GymApiService } from '../gym-api.service';

@Component({
  selector: 'app-front-end',
  templateUrl: './front-end.component.html',
  styleUrls: ['./front-end.component.css']
})
export class FrontEndComponent implements OnInit {

  public title : string = "";
  public isAccountClicked : boolean = false;

  constructor(protected gymApiService: GymApiService) { }

  ngOnInit(): void {
    this.retrieveConfig('title');
  }

  retrieveConfig(name: string){
    this.gymApiService.retrieveConfig(name).subscribe(config => this.title = config);
  }

  onClick(){
    if(this.isAccountClicked === false){
      this.isAccountClicked = true;
    }
    else{
      this.isAccountClicked = false;
    }
  }

}
