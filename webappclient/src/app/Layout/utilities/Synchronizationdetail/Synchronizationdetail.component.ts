import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-Synchronizationdetail',
  templateUrl: './Synchronizationdetail.component.html',
  styleUrls: ['./Synchronizationdetail.component.sass']
})
export class SynchronizationdetailComponent implements OnInit {
  active: number;

  constructor() { }

  ngOnInit(): void {
    this.active = 2;
  }

}
