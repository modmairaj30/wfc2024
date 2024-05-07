import { NgModule } from '@angular/core';
import { CommonModule} from '@angular/common';
import { TabModule } from '../Components/tabs/tab1.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UtilitiesComponent } from './utilities.component';
import { UtilitiesRoutingModule } from './utilities-routing.module';
import { UtilitiesdetailComponent } from './utilitiesdetail/utilitiesdetail.component';
import { UtilitiesentryComponent } from './utilitiesdetail/utilitiesentry/utilitiesentry.component';
import { TableModule } from 'primeng/table';
import {CalendarModule} from 'primeng/calendar';
import {SliderModule} from 'primeng/slider';
import {DialogModule} from 'primeng/dialog';
import {MultiSelectModule} from 'primeng/multiselect';
import {ContextMenuModule} from 'primeng/contextmenu';
import {ButtonModule} from 'primeng/button';
import {ToastModule} from 'primeng/toast';
import {InputTextModule} from 'primeng/inputtext';
import {ProgressBarModule} from 'primeng/progressbar';
import {DropdownModule} from 'primeng/dropdown';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { authInterceptorProviders } from 'src/app/DemoPages/UserPages/_helpers/auth.interceptor';
import {AutoCompleteModule} from 'primeng/autocomplete';
import { CustomSharedModule } from 'src/app/shared/custom-shared.module';
import { ToolbarModule } from 'primeng/toolbar';
import { ProductService, TransactionService } from '../service';
import {FileUploadModule} from 'primeng/fileupload';


@NgModule({
  imports: [
    CommonModule,
    UtilitiesRoutingModule,
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
    ToolbarModule,
    InputTextModule,
    ProgressBarModule,
    HttpClientModule,
    FormsModule,
    FileUploadModule,
    AutoCompleteModule,
    CustomSharedModule,
    ],
  exports: [
    UtilitiesComponent
   ],
  declarations: [
    UtilitiesComponent, UtilitiesdetailComponent, UtilitiesentryComponent  ],
  providers: [ UserService, authInterceptorProviders, ProductService, TransactionService],
})
export class UtilitiesModule { }
