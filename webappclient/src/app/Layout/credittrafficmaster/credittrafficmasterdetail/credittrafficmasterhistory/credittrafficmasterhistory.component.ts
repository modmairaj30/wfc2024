import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { ServerSidePagination } from 'src/app/Layout/domain/serversidepagination';
import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';
import { SearchCriteria } from 'src/app/layout/domain/searchCriteria.model';
import { GlobalConstants } from 'src/app/global-constants';

@Component({
  selector: 'app-credittrafficmasterhistory',
  templateUrl: './credittrafficmasterhistory.component.html',
  styleUrls: ['./credittrafficmasterhistory.component.css'],
  providers: [MessageService]
})
export class CredittrafficmasterHistoryComponent implements OnInit {
 
  user: any = [];
  loading:boolean=false;
  selectedRowData: any[];
  content: any[] = [];
  content1: any[] = [];

  cols: any[];
  searchs: any[];
  _selectedColumns: any[];
  selectedColumn: string;

  @Output()
  editCredittrafficmasterEntry = new EventEmitter();
  public serverSidePagination = new ServerSidePagination();
  public searchCriteria = new SearchCriteria();

  first = 0;

  rows = 10;

  @ViewChild("dt") dt;

  ngAfterViewInit(): void {
    debugger
    if (this.dt.totalRecords === 0) {
      this.dt.currentPageReportTemplate = this.dt.currentPageReportTemplate.replace("{first}", "0")
    }
  }

  constructor(private masterService: MasterService, private messageService: MessageService) {

    this.cols = [
      { field: 'locationId', header: 'Location Id' },
      { field: 'modeConsign', header: 'Mode Consign' },
      { field: 'type', header: 'Type' },
      { field: 'partyId', header: 'Customer' },
      { field: 'fromWeight', header: 'From Weight' },
      { field: 'toWeight', header: 'To Weight' },
      { field: 'docDt', header: 'Doc Date' },
      { field: 'docTime', header: 'Doc Time' },
      { field: 'userId', header: 'User' }
    ];

   this._selectedColumns= this.cols.filter((item) => item.checked === 1);
    this.searchs=GlobalConstants.searchs;
    this.searchCriteria.key=this.cols[0].field;
    this.searchCriteria.value="";
    this.searchCriteria.operation=this.searchs[0].field;

  }
  ngOnInit(): void {
    this.serverSidePagination.page = 0;
    this.serverSidePagination.size = 1000;
    this.loading=true;
    this.masterService.getCreditteriffMaster(this.serverSidePagination).subscribe(
      data => {

        this.content = JSON.parse(JSON.stringify(data.data));
        this.loading=false;
      },
      err => {
        this.content = JSON.parse(err.error).message;
        this.loading=false;
      }
    );
  }

  resetIndex(event) {
    //
    debugger;
   // this.content ? this.first === (this.content.length - this.rows) :true;
    if (this.isLastPage()) {
      this.serverSidePagination.page = this.serverSidePagination.page + 1;
      this.serverSidePagination.size = 1000;
      this.loading=true;
      this.masterService.getCreditteriffMaster(this.serverSidePagination).subscribe(
        data => {
          this.loading=false;
          this.content1 = JSON.parse(JSON.stringify(data.data));
          this.content = [...this.content, ...this.content1];
        },
        err => {
          this.loading=false;
          this.content = JSON.parse(err.error).message;
        }
      );
    }

  }

  isLastPage(): boolean {
    return this.content ? this.first === (this.content.length - this.rows) : true;
  }

  isFirstPage(): boolean {
    return this.content ? this.first === 0 : true;
  }
  onTableHeaderCheckboxToggle(event: any) {
  }
  editProduct(product: any) {
    this.editCredittrafficmasterEntry.emit(product);
  }

  find(): void {
    this.loading = true;
    debugger;
    this.masterService.findCreditTrafficMaster(this.searchCriteria).subscribe(
      data => {
        this.loading = false;
        this.content = JSON.parse(JSON.stringify(data.data));
      },
      err => {
        this.loading = false;
        this.content = JSON.parse(err.error).message;
      }
    );
  }

}
