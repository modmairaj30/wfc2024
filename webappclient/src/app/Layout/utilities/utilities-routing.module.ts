import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UtilitiesdetailComponent } from './utilitiesdetail/utilitiesdetail.component';

const routes: Routes = [
  { path: '',
component: UtilitiesdetailComponent,

}
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UtilitiesRoutingModule { }
