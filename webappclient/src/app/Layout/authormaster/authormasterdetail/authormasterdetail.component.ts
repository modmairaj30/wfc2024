import { Component, OnInit, ViewChild } from '@angular/core';
import { Authormaster } from '../../domain/authormaster.model';
import { AuthormasterentryComponent } from './authormasterentry/authormasterentry.component';


@Component({
  selector: 'app-authormasterdetail',
  templateUrl: './authormasterdetail.component.html',
  styleUrls: ['./authormasterdetail.component.css']
})
export class AuthormasterdetailComponent implements OnInit {
  active: number;

  authormaster = new Authormaster();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }
  editBranchentry(authormaster: any) {
    
    this.authormaster = authormaster;
    this.active = 1;
  }
  clear() {
    this.authormaster = new Authormaster();
  }
}
