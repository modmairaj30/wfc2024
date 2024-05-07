import { Component, OnInit } from '@angular/core';
import { Destinationmaster } from '../../domain';


@Component({
  selector: 'app-destinationdetail',
  templateUrl: './destinationdetail.component.html',
  styleUrls: ['./destinationdetail.component.css']
})
export class DestinationdetailComponent implements OnInit {
  active: 1;
  destinationmaster = new Destinationmaster();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }

  editDestination(destinationmaster: any) {

    this.destinationmaster = destinationmaster;
    this.active = 1;
  }
  clear() {
    this.destinationmaster = new Destinationmaster();
  }
  tabChange() {
    if (this.active == 1) {
      this.clear();
    }
  }

}
