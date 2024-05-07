import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TarifftypemasterdetailComponent } from './tarifftypemasterdetail/tarifftypemasterdetail.component';

const routes: Routes = [
 { path: '',
    component: TarifftypemasterdetailComponent,
   
  }
 
 ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TarifftypemasterRoutingModule { }
