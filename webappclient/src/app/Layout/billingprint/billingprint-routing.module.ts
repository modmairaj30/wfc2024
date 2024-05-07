import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BillingprintdetailComponent } from './billingprintdetail/billingprintdetail.component';

const routes: Routes = [
 { path: '',
    component: BillingprintdetailComponent,
   
  }
 
 ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BillingprintRoutingModule { }
