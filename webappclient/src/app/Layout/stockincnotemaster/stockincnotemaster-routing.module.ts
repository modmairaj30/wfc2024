import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StockincnotemasterdetailComponent } from './stockincnotemasterdetail/stockincnotemasterdetail.component';

const routes: Routes = [
 { path: '',
    component: StockincnotemasterdetailComponent,
   
  }
 
 ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StockincnoteMasterRoutingModule { }
