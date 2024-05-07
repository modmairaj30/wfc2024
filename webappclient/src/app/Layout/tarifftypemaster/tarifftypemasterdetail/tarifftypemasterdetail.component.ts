import { Component, OnInit, ViewChild } from '@angular/core';
import { Branchmaster, TariffType } from '../../domain';
import { TarifftypemasterentryComponent } from './tarifftypemasterentry/tarifftypemasterentry.component';


@Component({
  selector: 'app-tarifftypemasterdetail',
  templateUrl: './tarifftypemasterdetail.component.html',
  styleUrls: ['./tarifftypemasterdetail.component.css']
})
export class TarifftypemasterdetailComponent implements OnInit {
  active: 1;
  tariffType = new TariffType();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }

  editTariffType(tariffType: any) {

    this.tariffType = tariffType;
    this.active = 1;
  }
  clear() {
    this.tariffType = new TariffType();
  }
  tabChange() {
    if (this.active == 1) {
      this.clear();
    }
  }

}
