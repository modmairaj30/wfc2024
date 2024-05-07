import { Component, OnInit, ViewChild } from '@angular/core';
import { Branchmaster } from '../../domain';
import { BranchentryComponent } from './branchentry/branchentry.component';


@Component({
  selector: 'app-branchdetail',
  templateUrl: './branchdetail.component.html',
  styleUrls: ['./branchdetail.component.css']
})
export class BranchdetailComponent implements OnInit {
  active: number;

  branchmaster = new Branchmaster();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }
  editBranchentry(branchmaster: any) {
    
    this.branchmaster = branchmaster;
    this.active = 1;
  }
  clear() {
    this.branchmaster = new Branchmaster();
  }
}
