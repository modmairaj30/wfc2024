import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { QueryList, ViewChildren } from '@angular/core';
import { Observable } from 'rxjs';
import { ServerSidePagination } from 'src/app/Layout/domain/serversidepagination';


import {
  UserService,
} from 'src/app/DemoPages/UserPages/_services/user.service';
import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-stockincnotemasterhistory',
  templateUrl: './stockincnotemasterhistory.component.html',
  styleUrls: ['./stockincnotemasterhistory.component.css'],
  providers: [MessageService]
})
export class StockincnotemasterHistoryComponent implements OnInit {

  user: any = [];
  selectedRowData: any[];
  content: any[] = [];
  content1: any[] = [];
  @Output()
  editCardTypeEntry = new EventEmitter();
  public serverSidePagination = new ServerSidePagination();

  first = 0;

  rows = 10;

  @ViewChild("dt") dt;

  ngAfterViewInit(): void {
    debugger
    if (this.dt.totalRecords === 0) {
      this.dt.currentPageReportTemplate = this.dt.currentPageReportTemplate.replace("{first}", "0")
    }
  }

  constructor(private userService: MasterService, private messageService: MessageService) {

  }
  ngOnInit(): void {
    this.serverSidePagination.page = 10;
    this.serverSidePagination.size = 100;
    this.userService.getAllPartys(this.serverSidePagination).subscribe(
      data => {

        this.content = JSON.parse(JSON.stringify(data.data));
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

  }

  resetIndex(event) {
    //
    if (this.isLastPage()) {
      this.serverSidePagination.page = this.serverSidePagination.page + 10;
      this.serverSidePagination.size = 100;

      this.userService.getAllPartys(this.serverSidePagination).subscribe(
        data => {
          this.content1 = JSON.parse(JSON.stringify(data.data));
          this.content = [...this.content, ...this.content1];
        },
        err => {
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
    this.editCardTypeEntry.emit(product);
  }
}
