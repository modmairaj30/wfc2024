import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DestinationdetailComponent } from './destinationdetail/destinationdetail.component';

const routes: Routes = [
 { path: '',
    component: DestinationdetailComponent,
   
  }
 
 ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DestinationMasterRoutingModule { }
