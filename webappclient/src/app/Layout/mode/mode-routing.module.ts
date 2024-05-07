import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModedetailComponent } from './modedetail/modedetail.component';

const routes: Routes = [
 { path: '',
    component: ModedetailComponent,
   
  }
 
 ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModeRoutingModule { }
