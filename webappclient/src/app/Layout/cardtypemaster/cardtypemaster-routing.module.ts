import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardtypedetailComponent } from './cardtypedetail/cardtypedetail.component';

const routes: Routes = [
 { path: '',
    component: CardtypedetailComponent,
   
  }
 
 ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CardtypeMasterRoutingModule { }
