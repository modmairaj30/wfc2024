import { Component, OnInit } from '@angular/core';
import { Invoice } from '../../domain';



@Component({
  selector: 'app-billingprintdetail',
  templateUrl: './billingprintdetail.component.html',
  })
export class BillingprintdetailComponent implements OnInit {
  active: number;
 editmod: boolean=false;
 invoice = new Invoice();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
   // this.editmod=false;
   // this.titlemaster = titlemaster;
  }
  editTitleMasterentry(invoice: any) {
    
    this.editmod=true;
    this.invoice = invoice;
    this.active = 1;
  }
editEntry($event){
    this.active =2;
   // this.editmod=false;
    this.invoice = new Invoice();
  }
  clear() {
 this.editmod=false;
    this.invoice = new Invoice();
  }
setTab(){
  this.editmod=false;
  this.invoice = new Invoice();
}

}
