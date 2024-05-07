import { Component, OnInit, ViewChild } from '@angular/core';

import { ModeentryComponent } from './modeentry/modeentry.component';
import { Mode } from '../../domain';


@Component({
  selector: 'app-modedetail',
  templateUrl: './modedetail.component.html',
  styleUrls: ['./modedetail.component.css']
})
export class ModedetailComponent implements OnInit {
  active: number;

  mode = new Mode();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }
  editMode(mode: any) {

    this.mode = mode;
    this.active = 1;
  }
  clear() {
    this.mode = new Mode();
  }
  tabChange() {
    if (this.active == 1) {
      this.clear();
    }
  }
}
