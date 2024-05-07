import { Component, OnInit, EventEmitter, Output, ViewChild, Input } from '@angular/core';
import { MessageService } from 'primeng/api';
import { GlobalConstants } from 'src/app/global-constants';
import { ServerSidePagination } from 'src/app/layout/domain';
import { SearchCriteria } from 'src/app/layout/domain/searchCriteria.model';
import { MasterService } from 'src/app/layout/service';

@Component({
  selector: 'app-destinationhistory',
  templateUrl: './destinationhistory.component.html',
  styleUrls: ['./destinationhistory.component.css'],
  providers: []
})
export class DestinationHistoryComponent implements OnInit {
  selectedRowData: any[];
  user: any = [];
  loading: boolean = false;
  content: any[] = [];
  content1: any[] = [];
  cols: any[];
  searchs: any[];
  _selectedColumns: any[];
  selectedColumn: string;
  
  @Output()
  editDestinationEntry = new EventEmitter();
  public serverSidePagination = new ServerSidePagination();
  public searchCriteria = new SearchCriteria();
  
  first = 0;
  rows = 100;

  @ViewChild("dt") dt;
  
  
  constructor(private masterService: MasterService, private messageService: MessageService) {
    this.cols = [
      {  checked:1,width: 'width:10%', field: 'destination', header: 'Destination' },
      {  checked:1,width: 'width:10%', field: 'dest_code', header: 'Dest Code' },
      {  checked:1,width: 'width:10%', field: 'branch', header: 'Branch' },
      {  checked:1,width: 'width:20%', field: 'zone', header: 'Zone' },
      {  checked:1,width: 'width:15%', field: 'parent_local', header: 'Parent Local' },
      {  checked:1,width: 'width:15%', field: 'state_code', header: 'State Code' }
    ];
      this._selectedColumns= this.cols.filter((item) => item.checked === 1);
      this.searchs=GlobalConstants.searchs;
      this.searchCriteria.key=this.cols[0].field;
      this.searchCriteria.value="";
      this.searchCriteria.operation=this.searchs[0].field;
      
    }
    ngOnChanges(): void {
      
      console.log("===" + this.isLastPage());
    }
  ngOnInit(): void {
    this.first = 0,
    this.rows = 100,
    this.serverSidePagination.page = 0,
    this.serverSidePagination.size = 300,
    this.loading = true,

    this.masterService.getDestinations().subscribe(
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
    this.editDestinationEntry.emit(product);
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

    this.serverSidePagination.page = this.serverSidePagination.page + 1,
    this.serverSidePagination.size = 300,
    this.loading = true,
    this.masterService.getDestinations().subscribe(
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

}
