import { Component, OnInit, ViewChild, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { ServerSidePagination } from 'src/app/Layout/domain/serversidepagination';


import { MasterService, TransactionService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';
import { GlobalConstants } from 'src/app/global-constants';
import { SearchCriteria } from 'src/app/layout/domain/searchCriteria.model';
import { ReportInput } from 'src/app/layout/domain';
import { ReportService } from 'src/app/Layout/service/reportservice';

@Component({
  selector: 'app-approvalmemohistory',
  templateUrl: './approvalmemohistory.component.html',
  styleUrls: ['./approvalmemo.scss'],
  providers: [MessageService]
})
export class ApprovalmemoHistoryComponent  implements OnInit, OnChanges{
  
  user: any = [];
  loading: boolean = false;
  selectedRowData: any[];
  content: any[] = [];
  content1: any[] = [];
  cols: any[];
  searchs: any[];
  _selectedColumns: any[];
  selectedColumn: string;

  @Output()
  editTitleMasterentry = new EventEmitter();
  public serverSidePagination = new ServerSidePagination();
  public searchCriteria = new SearchCriteria();
  
  first = 0;
  rows = 100;

  public reportInput = new ReportInput();

  @ViewChild("dt") dt;
  constructor(private masterService: MasterService,private reportService: ReportService, private messageService: MessageService, private ts: TransactionService) {
    this.cols = [
      {  checked:1,width: 'width:10%', field: 'id', header: 'Id' },
      {  checked:1,width: 'width:10%', field: 'invoiceNo', header: 'invoiceNo' },
      {  checked:1,width: 'width:10%', field: 'docDt', header: 'Doc Date' },
      {  checked:1,width: 'width:20%', field: 'refNo', header: 'refNo' },
      {  checked:1,width: 'width:15%', field: 'partyLabel', header: 'partyLabel' },
      {  checked:1,width: 'width:10%', field: 'cNoteNo', header: 'cNoteNo' },
      // checked:0,width: 'width:10%', { field: 'category', header: 'Category' },
     //  checked:0,width: 'width:10%', { field: 'rate', header: 'Rate' },
     //  checked:0,width: 'width:10%', { field: 'discount', header: 'Discount' },
      {  checked:0,width: 'width:10%', field: 'custName', header: 'custName' },
      {  checked:0,width: 'width:10%', field: 'amount', header: 'amount' },
     //  checked:0,width: 'width:10%', { field: 'description', header: 'Description' },
      {  checked:1,width: 'width:15%', field: 'weight', header: 'weight' },
      {  checked:1,width: 'width:15%', field: 'noOfPieces', header: 'noOfPieces' },
      {  checked:1,width: 'width:15%', field: 'upload', header: 'upload' },
      {  checked:1,width: 'width:10%', field: 'uploadTime', header: 'uploadTime' },
      {  checked:1,width: 'width:10%', field: 'cardType', header: 'cardType' },
      {  checked:0,width: 'width:10%', field: 'locationId', header: 'locationId' },
      {  checked:0,width: 'width:10%', field: 'decPer', header: 'decPer' },
      {  checked:0,width: 'width:10%', field: 'docketCharges', header: 'docketCharges' },
      {  checked:0,width: 'width:10%', field: 'type', header: 'type' },
      {  checked:0,width: 'width:10%', field: 'odCharges', header: 'odCharges' },
      {  checked:0,width: 'width:10%', field: 'userId', header: 'userId' }
    ];
   // this._selectedColumns = this.cols;

   // let selectedDefault= [];
   this._selectedColumns= this.cols.filter((item) => item.checked === 1);
    //this._selectedColumns.map((item) => this._selectedColumns.push(item.cheded));
    this.searchs=GlobalConstants.searchs;
    this.searchCriteria.key=this.cols[0].field;
    this.searchCriteria.value="";
    this.searchCriteria.operation=this.searchs[0].field;
    
  }
  ngOnChanges(): void {
    
    console.log("===" + this.isLastPage());
  }
  ngOnInit(): void {
    this.first = 0;
    this.rows = 100;
    this.serverSidePagination.page = 0;
    this.serverSidePagination.size = 300;
    this.loading = true;
    this.ts.getAllInvoice(this.serverSidePagination).subscribe(
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

  editProduct(product: any) {
    this.editTitleMasterentry.emit(product);
  }
  onTableHeaderCheckboxToggle(event: any) {
  }
  @Input() get selectedColumns(): any[] {
    return this._selectedColumns;
  }

  set selectedColumns(val: any[]) {
    //restore original order
    this._selectedColumns = this.cols.filter(col => val.includes(col));
  }

  next1() {

    this.serverSidePagination.page = this.serverSidePagination.page + 1;
    this.serverSidePagination.size = 300;
    this.loading = true;
    this.masterService.getAllTitleMaster(this.serverSidePagination).subscribe(
      data => {
        this.loading = false;
        this.content1 = JSON.parse(JSON.stringify(data.data));
        this.content = [...this.content, ...this.content1];
      },
      err => {
        this.loading = false;
        this.content = JSON.parse(err.error).message;
      }
    );


  }

  next() {
    this.first = this.first + this.rows;
  }

  prev() {
    this.first = this.first - this.rows;
  }

  reset() {
    this.first = 0;
  }

  isLastPage(): boolean {
    return this.content ? this.first === (this.content.length - this.rows) : true;
  }

  isFirstPage(): boolean {
    return this.content ? this.first === 0 : true;
  }
  pdf(){

  
  
    //let copt =this.reportInput;
    this.reportInput.id=11;
    this.reportInput.reportName="billing";
    
    let copy = JSON.parse(JSON.stringify(this.reportInput));
     
    //  copy.locationId=copy.locationId.id;
      this.loading=true;
          this.reportService.print(this.reportInput).subscribe((blob: Blob): void => {
        this.loading=false;
        
        const file = new Blob([blob], {type: 'application/pdf'});
        const fileURL = URL.createObjectURL(file);
        window.open(fileURL, '_blank', 'width=1000, height=800');
      });
    }

  find(): void {
    this.loading = true;
    debugger;
    this.masterService.findTitleMaster(this.searchCriteria).subscribe(
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
