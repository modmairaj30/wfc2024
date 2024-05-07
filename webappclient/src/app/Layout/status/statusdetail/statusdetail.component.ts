import { Component, OnInit, ViewChild } from '@angular/core';
import { Branchmaster, Status } from '../../domain';
import { StatusentryComponent } from './statusentry/statusentry.component';


@Component({
  selector: 'app-statusdetail',
  templateUrl: './statusdetail.component.html',
  styleUrls: ['./statusdetail.component.css']
})
export class StatusdetailComponent implements OnInit {
  active: 1;
  status1 = new Status();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }

  editStatus(status1: any) {
    debugger;
    this.status1 = status1;
    this.active = 1;
  }
  clear() {
    this.status1 = new Status();
  }
  tabChange() {
    if (this.active == 1) {
      this.clear();
    }
  }

}
