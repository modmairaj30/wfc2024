import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SynchronizationdetailComponent } from './Synchronizationdetail/Synchronizationdetail.component';

const routes: Routes = [
  { path: '',
component: SynchronizationdetailComponent,

}
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SynchronizationRoutingModule { }
