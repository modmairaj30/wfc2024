import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CredittrafficmasterdetailComponent } from './credittrafficmasterdetail/credittrafficmasterdetail.component';

const routes: Routes = [
 { path: '',
    component: CredittrafficmasterdetailComponent,
   
  }
 
 ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CredittrafficMasterRoutingModule { }
