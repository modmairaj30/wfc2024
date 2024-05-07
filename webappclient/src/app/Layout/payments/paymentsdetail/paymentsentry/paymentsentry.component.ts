import { Component, OnInit, Input, Output, EventEmitter, ViewChild, HostListener, ElementRef } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { MasterService, TransactionService } from 'src/app/layout/service';
import { Payment  } from 'src/app/layout/domain';
import { Router} from '@angular/router';
import { MessageService } from 'primeng/api';
import { AutoComplete } from 'primeng/autocomplete';
import { NgModel } from '@angular/forms';
//@import "src/style/entryscreen.scss";

@Component({
  selector: 'app-paymentsentry',
  templateUrl: './paymentsentry.component.html',//
  styleUrls: ['./paymentsentry.component.scss']
})
export class PaymentsentryComponent implements OnInit {

  @ViewChild('formForm') formForm;
  @ViewChild('fe') input: AutoComplete;


  @Output()
  editEntry = new EventEmitter();

  dateValue: Date;
  @Input()
  editmod: boolean = false;


  
  empList1: any[] = [];
  localList5: any[] = [];
  
  map = new Map();
  error: string;
  errorMessage:string

  @Input()
  public payment = new Payment();
   


  filteredEmpList1: any[];
  filteredList5: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean5 = new LookUpFormBean();

  listLookUpFormBean: LookUpFormBean[] = new Array();

  constructor(private messageService: MessageService,private userService: UserService, private transactionService: TransactionService) { }

  ngOnInit(): void {
    /////list
    this.payment.userId= window.sessionStorage.getItem("userName");
    this.lookUpFormBean1.query = "select id,party_name,party_type from tbl_party_info where party_type=2";
    this.lookUpFormBean5.query = "select id,location_name from tbl_location";

    this.lookUpFormBean1.listName = "LIST1";
    this.lookUpFormBean5.listName = "LIST5";

    this.listLookUpFormBean.push(this.lookUpFormBean1);
    this.listLookUpFormBean.push(this.lookUpFormBean5);
    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.empList1 = JSON.parse(JSON.stringify(data.data.LIST1));
        this.localList5 = JSON.parse(JSON.stringify(data.data.LIST5));
        if (this.editmod) {
          this.editmode();
        }else{
            
          setTimeout(() => {

          this.input.focusInput();
          }, 1000);

   this.payment.docDt= new Date();
    let location= window.sessionStorage.getItem("location");
 this.payment.locationId = this.localList5.find(x => x.id == location.split(",")[0]);
 
        }

      },
      err => {
        this.map = JSON.parse(err.error).message;
      }
    );
    
  }

  filterEmpList1(event) {
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.empList1.length; i++) {
      let item = this.empList1[i];
     // if (item.party_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
      if (item.party_name.toLowerCase().includes(query.toLowerCase())) {
        filtered.push(item);
      }
    }

    this.filteredEmpList1 = filtered;
  }

  
  filterList5(event) {
   debugger;
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.localList5.length; i++) {
      let item = this.localList5[i];
     // if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        if (item.location_name.toLowerCase().includes(query.toLowerCase())) {  
        filtered.push(item);
      }
    }

    this.filteredList5 = filtered;
  }

  //saving logics

  save() {
  //this.elRef.focusInput();
  
    let copy = JSON.parse(JSON.stringify(this.payment))
    
    copy.locationId=copy.locationId.id
    copy.partyId=copy.partyId.id
    this.transactionService.savePayments(copy).subscribe(
      data => {
        if (data.statusCode == "OK") {
          this.showSuccess();
          this.reset();
        }
        else {
          this.errorMessage = data.webAppException.webAppError[0].reason;
          this.showFail(this.errorMessage);
        }
        if (this.editmod) {

          this.editEntry.emit();
        }
      },
      err => {
        this.map = JSON.parse(err.error).message;
      }
    );
  }

  public checkSelectOptionValue(value) {
    if (value == "undefined" || value == null) {
      return value;
    }
  }

  reset() {
    this.formForm.resetForm();
    this.payment = new Payment();
    setTimeout(() => {
      if (this.editmod) {
        //  this.editmod=false;
        //  this.reset();
        // this.router.navigate(['/purchaseInvoice/purchaseInvoice']);
        this.editEntry.emit(this.editmod);
      }
      else {
        this.input.focusInput();
      }
    }, 1500);

  }
  editmode() {

    this.payment.partyId = this.empList1.find(x => x.id == this.payment.partyId);
    this.payment.locationId = this.localList5.find(x => x.id == this.payment.locationId);
    this.payment.docDt=new Date(this.payment.docDt);
  }

  @HostListener('keydown.F2', ['$event']) F2($event: KeyboardEvent) {
    if (this.formForm.form.valid) { this.save(); }

  }
  @HostListener('keydown.F5', ['$event']) F5($event: KeyboardEvent) {
    this.reset();
  }
  showSuccess() {
    this.messageService.add({ key: 'success', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }
  showFail(value: string) {
    this.messageService.add({ key: 'error', severity: 'error', summary: 'error', detail: value });
  }

}



