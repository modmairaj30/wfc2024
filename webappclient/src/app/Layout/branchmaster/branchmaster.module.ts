import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TabModule } from '../Components/tabs/tab1.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BranchmasterComponent } from './branchmaster.component';
import { BranchMasterRoutingModule } from './branchmaster-routing.module';
import { BranchdetailComponent } from './branchdetail';
import { BranchentryComponent } from './branchdetail/branchentry/branchentry.component';
import { BranchHistoryComponent } from './branchdetail/branchhistory/branchhistory.component';
import { TableModule } from 'primeng/table';
import { CalendarModule } from 'primeng/calendar';
import { SliderModule } from 'primeng/slider';
import { DialogModule } from 'primeng/dialog';
import { MultiSelectModule } from 'primeng/multiselect';
import { ContextMenuModule } from 'primeng/contextmenu';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { ProgressBarModule } from 'primeng/progressbar';
import { DropdownModule } from 'primeng/dropdown';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { authInterceptorProviders } from 'src/app/DemoPages/UserPages/_helpers/auth.interceptor';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { CustomSharedModule } from 'src/app/shared/custom-shared.module';
import { ToolbarModule } from 'primeng/toolbar';
import { TooltipModule } from 'primeng/tooltip';




@NgModule({
  imports: [
    CommonModule,
    BranchMasterRoutingModule,
    TabModule,
    NgbModule,
    TableModule,
    CalendarModule,
    SliderModule,
    DialogModule,
    MultiSelectModule,
    ContextMenuModule,
    DropdownModule,
    ButtonModule,
    ToastModule,
    InputTextModule,
    ProgressBarModule,
    HttpClientModule,
    FormsModule,
    AutoCompleteModule,
    CustomSharedModule,
    TooltipModule,
    ToolbarModule
  ],
  exports: [
    BranchmasterComponent
  ],
  declarations: [
    BranchmasterComponent, BranchdetailComponent, BranchentryComponent, BranchHistoryComponent
  ],
  providers: [UserService, authInterceptorProviders],
})
export class BranchMasterModule { }
