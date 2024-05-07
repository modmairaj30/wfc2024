import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-Utilitiesdetail',
  templateUrl: './Utilitiesdetail.component.html',
  styleUrls: ['./Utilitiesdetail.component.sass']
})
export class UtilitiesdetailComponent implements OnInit {
  active: number;

  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }

}
