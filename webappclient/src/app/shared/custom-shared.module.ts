import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoadingBarRouterModule } from '@ngx-loading-bar/router';
import { NgxLoadingModule } from 'ngx-loading';
import { MessageService } from 'primeng/api';
import { DialogModule } from 'primeng/dialog';
import { KeyFilterModule } from 'primeng/keyfilter';
import {CalendarModule} from 'primeng/calendar';
import { CardModule, } from 'primeng/card';
import { ListboxModule } from 'primeng/listbox';
import { MessagesModule } from 'primeng/messages';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import {DividerModule} from 'primeng/divider';
import { AlphaNumeric } from './directive/alphanumeric.directive';
import { DecimalPlaces } from './directive/decimalplaces.directive';
import { DecimalnumberDirective } from './directive/decimalnumber.directive';
import { DefaultZeroDirective } from './directive/default-zero.directive';
import { OnlyNumber } from './directive/onlynumber.directive';
import { OnlyString } from './directive/onlystring.directive';
import { DatexPipe } from './pipes/date.pipe';
import {InputNumberModule} from 'primeng/inputnumber';
import { AutofocusDirective } from './directive/autofocus.directive';
import { AutofocusForAutoCompleteDirective } from './directive/autofocusautocomplete.directive';
import { TableNavigationDirective } from './directive/tablenavigation.directive';
import { AlphaNumericNoSpace } from './directive/alphanumericnospace.directive';
import { ExportToExcelComponent, ExportToPdfComponent, PrintPageComponent } from './components';
import { ButtonModule } from 'primeng/button';
import { FilterPipe } from './pipes/filter.pipe';
import { ExportToPdfReportComponent } from './components/reportpdf';



@NgModule({
    imports: [CommonModule,CardModule,ButtonModule,DividerModule,CalendarModule,ListboxModule, InputNumberModule, ScrollPanelModule, ToastModule, ToolbarModule, MessagesModule, LoadingBarRouterModule, DialogModule,DividerModule, NgxLoadingModule,KeyFilterModule, ProgressSpinnerModule, NgbModule],
    declarations: [AutofocusDirective,TableNavigationDirective, AutofocusForAutoCompleteDirective, OnlyNumber, OnlyString, AlphaNumeric,AlphaNumericNoSpace, DatexPipe,FilterPipe, DefaultZeroDirective, DecimalPlaces, DecimalnumberDirective,PrintPageComponent,ExportToExcelComponent,ExportToPdfComponent,ExportToPdfReportComponent],
    providers: [MessageService],
    exports: [AutofocusDirective,TableNavigationDirective,CardModule,CalendarModule, AutofocusForAutoCompleteDirective, OnlyNumber, OnlyString, AlphaNumeric,AlphaNumericNoSpace, DatexPipe,FilterPipe, DefaultZeroDirective,DecimalnumberDirective, DecimalPlaces, ToolbarModule, MessagesModule, ToastModule, KeyFilterModule, NgxLoadingModule, InputNumberModule,PrintPageComponent,ExportToExcelComponent,ExportToPdfComponent,ExportToPdfReportComponent],
})
export class CustomSharedModule { }
