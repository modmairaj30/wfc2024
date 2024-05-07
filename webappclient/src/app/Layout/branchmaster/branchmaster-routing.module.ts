import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BranchdetailComponent } from './branchdetail/branchdetail.component';

const routes: Routes = [
 { path: '',
    component: BranchdetailComponent,
   
  }
 
 ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchMasterRoutingModule { }
