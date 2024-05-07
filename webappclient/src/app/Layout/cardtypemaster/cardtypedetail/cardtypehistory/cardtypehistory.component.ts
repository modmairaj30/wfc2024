import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { QueryList, ViewChildren } from '@angular/core';
import { Observable } from 'rxjs';


import {
  UserService,
} from 'src/app/DemoPages/UserPages/_services/user.service';
import { MessageService } from 'primeng/api';
import { MasterService } from 'src/app/layout/service';

@Component({
  selector: 'app-cardtypehistory',
  templateUrl: './cardtypehistory.component.html',
  styleUrls: ['./cardtypehistory.component.scss'],
  providers: [MessageService]
})
export class CardtypeHistoryComponent implements OnInit {
  selectedRowData: any[];
  user: any = [];
  content: any[] = [];

  @Output()
  editCardTypeEntry = new EventEmitter();

  constructor(private masterService: MasterService, private messageService: MessageService) {

  }
  ngOnInit(): void {

    this.masterService.getAllCardType().subscribe({
      next: (response) => {
         this.content = JSON.parse(JSON.stringify(response.data));
         
      },
      error: (error) => {
          alert('There was an error in retrieving data from the server');
      }
  });
    //  this.loadEmployees()
  }
  onTableHeaderCheckboxToggle(event: any) {
  }
  editProduct(product: any) {
    this.editCardTypeEntry.emit(product);
  }
}



