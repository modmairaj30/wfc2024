import { Component, OnInit, ViewChild, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { ServerSidePagination } from 'src/app/Layout/domain/serversidepagination';
import { MessageService } from 'primeng/api';
import { LocationService, MasterService, TransactionService } from 'src/app/layout/service';
import { SearchCriteria } from 'src/app/Layout/domain/searchCriteria.model';
import { GlobalConstants } from 'src/app/global-constants';
import { DatexPipe } from 'src/app/shared/pipes/date.pipe';
import { FilterPipe } from 'src/app/shared/pipes/filter.pipe';
import { LookUpFormBean, ReportInput, StockVerification, Synchronize } from 'src/app/layout/domain';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ReportService } from 'src/app/Layout/service/reportservice';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { NormalReportService } from 'src/app/Layout/service/normalreportservice';
import { JasperDynamicReportService } from 'src/app/Layout/service/jasperdynamicreportservice';


@Component({
  selector: 'app-Synchronizationentry',
  templateUrl: './Synchronizationentry.component.html',
  styleUrls: ['./Synchronizationentry.component.sass'],
  providers: [MessageService]
})
export class SynchronizationentryComponent implements OnInit {

  synchronize = new Synchronize();





  constructor(private userService: UserService, private reportService: NormalReportService, private messageService: MessageService, private masterService: MasterService, private jasperService: JasperDynamicReportService) {
 
    

  }
  ngOnInit(): void {
    

  }

     

  upload() {
    debugger;
           
    this.masterService.uploadDataToServer(this.synchronize).subscribe(
      data => {
        if (data.body.message == 'ok') {
          this.showSuccess();
        }
        else {
          this.showFail(data.body.message);

        }
        // this.usermaster = new UserMaster();
      },
      err => {
      //  this.map = JSON.parse(err.error).message;
      }
    );
  }

  download() {
    debugger;
           
    this.masterService.downloadDataFromServer(this.synchronize).subscribe(
      data => {
        if (data.body.message == 'ok') {
          this.showSuccess();
        }
        else {
          this.showFail(data.body.message);

        }
        // this.usermaster = new UserMaster();
      },
      err => {
      //  this.map = JSON.parse(err.error).message;
      }
    );
  }

  showSuccess() {
    this.messageService.add({ key: 'success', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }
  showFail(value: string) {
    this.messageService.add({ key: 'error', severity: 'error', summary: 'error', detail: value });
  }


}


