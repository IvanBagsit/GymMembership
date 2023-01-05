import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-confirm-update-information',
  templateUrl: './confirm-update-information.component.html',
  styleUrls: ['./confirm-update-information.component.css']
})
export class ConfirmUpdateInformationComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  returnToPreviousPage() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

}
