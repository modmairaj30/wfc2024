import { Component, OnInit, ViewChild, Input, Output, EventEmitter, HostListener, ElementRef } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';

import { MasterService, TransactionService } from 'src/app/layout/service';
import { LookUpFormBean, StockInCNote, StockInCNoteDetail, StockVerification, StockVerificationDetail } from 'src/app/layout/domain';
import { Router} from '@angular/router';
import { MessageService } from 'primeng/api';
import { AutoComplete } from 'primeng/autocomplete';
import { NgModel } from '@angular/forms';
//import { LocationService } from 'src/app/Layout/service/locationservice';
//@import "src/style/entryscreen.scss";

@Component({
  selector: 'app-stockverificationentry',
  templateUrl: './stockverificationentry.component.html',//
  styleUrls: ['./stockverificationentry.component.scss']
})
export class StockverificationentryComponent implements OnInit {

  @ViewChild('detailtable') detailtable;
  @ViewChild('formForm') formForm;
  @ViewChild('fe') input: AutoComplete;
  
  //@ViewChild('localOS') private localOS: AutoComplete;

  //ViewChild returns ElementRef i.e. input HTML Element
 
//@ViewChild('localOS',{static:false, read: ElementRef}) elRef;
 
 
//ViewChild returns NgModel associated with the nameInput
//@ViewChild('nameInput',{static:false, read: NgModel}) inRef;

  @Output()
  editEntry = new EventEmitter();

  dateValue: Date;
  @Input()
  editmod: boolean=false;
  sno: number = 0;
  values = [];
  titleList2: any[] = [];
  locationList1: any[] = [];

  map = new Map();
  error: string;
  errorMessage:string

  @Input()
  public stockInCNote = new StockInCNote();
   //stockVerificationDetailList = new Array<StockVerificationDetail>();
  products2: StockInCNoteDetail[] = new Array();
  clonedProducts: { [s: string]: StockInCNoteDetail; } = {};

  filteredEmpList1: any[];
  filteredEmpList2: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();

  listLookUpFormBean: LookUpFormBean[] = new Array();

  constructor(private messageService: MessageService,private userService: UserService, private transactionService: TransactionService) { }

  ngOnInit(): void {
    /////list
    this.stockInCNote.uid=window.sessionStorage.getItem("userName");
    this.lookUpFormBean1.query = "select id,location_name,location_code from tbl_location "; //where id in("+sessionStorage.getItem("location")+")";
    this.lookUpFormBean2.query = "SELECT id, prod_name,product_code,author,product_rate, discount, supp_name, publisher_name FROM title_view";

    this.lookUpFormBean1.listName = "LIST1";
    this.lookUpFormBean2.listName = "LIST2";

    this.listLookUpFormBean.push(this.lookUpFormBean1);
    this.listLookUpFormBean.push(this.lookUpFormBean2);


    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.locationList1 = JSON.parse(JSON.stringify(data.data.LIST1));
        this.titleList2 = JSON.parse(JSON.stringify(data.data.LIST2));
        if (this.editmod) {
                    this.editmode();
        }else{
	      
        setTimeout(() => {
          
          this.input.focusInput();
        }, 1000);
     
         this.stockInCNote.docDt= new Date();
        let location= window.sessionStorage.getItem("location");
         this.stockInCNote.locationId = this.locationList1.find(x => x.id == location.split(",")[0]);
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
    for (let i = 0; i < this.locationList1.length; i++) {
      let item = this.locationList1[i];
    //  if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
      if (item.location_name.toLowerCase().includes(query.toLowerCase())) { 
        filtered.push(item);
      }
    }

    this.filteredEmpList1 = filtered;
  }

  filterEmpList2(event) {
    let filtered: any[] = [];
    let query = event.query;
    
    for (let i = 0; i < this.titleList2.length; i++) {
      let item = this.titleList2[i];
      //if (item.prod_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        if (item.prod_name.toLowerCase().includes(query.toLowerCase())) {
        filtered.push(item);
      }
    }
    //debugger
    this.filteredEmpList2 = filtered;
  }


  //saving logics

  save() {
    debugger;
    //this.elRef.focusInput();
    this.stockInCNote.stockincnoteDetailList = this.products2;
    let copy = JSON.parse(JSON.stringify(this.stockInCNote));
    copy.cPrefix = copy.cPrefix.toUpperCase();
    copy.userId=window.sessionStorage.getItem("userName");
    copy.locationId=copy.locationId.id
    copy.cPrefix=copy.cPrefix.toUpperCase();
    /*
    copy.stockVerificationDetailList.forEach((item, indexa) => {
    copy.stockVerificationDetailList[indexa].productId = copy.stockVerificationDetailList[indexa].productId.id;
    });
   */
    
    this.transactionService.saveStockInCNote(copy).subscribe(
      data => {
        if (data.statusCode === "OK") {
          this.showSuccess();
          
          this.reset();
        }
        
        else {
          this.errorMessage = data.webAppException.webAppError[0].reason;
          this.showFail(this.errorMessage);
          
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


  //table logics

  change3(event) {
     this.stockInCNote.docNo=event.location_code+"-SC-";
    }
  editmode() {
    this.products2=this.stockInCNote.stockincnoteDetailList;
    this.stockInCNote.docDt=new Date(this.stockInCNote.docDt);
   this.stockInCNote.locationId = this.locationList1.find(x => x.id == this.stockInCNote.locationId);
   for(let i = 0; i < this.stockInCNote.stockincnoteDetailList.length; i++){
    this.stockInCNote.stockincnoteDetailList[i].sno = i+1; 
    this.sno=i+1;
   }
   /*
   for(let i = 0; i < this.stockVerification.stockVerificationDetailList.length; i++){
    this.stockVerification.stockVerificationDetailList[i].productId = this.titleList2.find(x => x.id == this.stockVerification.stockVerificationDetailList[i].productId); 
   }*/
    // this.branchmaster.localOS = this.localList2.find(x => x.id == this.branchmaster.localOS);
   // this.stockVerification.parent = this.parentlocationList3.find(x => x.id == this.stockVerification.parent);
  }
  reset() {
    //window.location.reload();
    debugger;
   
    this.formForm.resetForm();
    this.stockInCNote = new StockInCNote();
    this.products2= new Array();
    this.clonedProducts={};
    this.sno = 0;
    
        setTimeout(() => {
          if (this.editmod) {
          //  this.editmod=false;
            //  this.reset();
            // this.router.navigate(['/purchaseInvoice/purchaseInvoice']);
            this.editEntry.emit(this.editmod);
          }
          else{
            this.input.focusInput();
            this.stockInCNote.docDt= new Date();
            let location= window.sessionStorage.getItem("location");
             this.stockInCNote.locationId = this.locationList1.find(x => x.id == location.split(",")[0]);
            
          }
        }, 1500);
    
        

  }

@HostListener('keydown.F2', ['$event']) F2($event: KeyboardEvent) {
    if(this.formForm.form.valid){this.save();}
}
@HostListener('keydown.F5', ['$event']) F5($event: KeyboardEvent) { 
    this.reset();
  }
showSuccess() {
  this.messageService.add({ key: 'success', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
}
showFail(value:string) {
  this.messageService.add({ key: 'error', severity: 'error', summary: 'error', detail:value });
}

calculatate(event){
  
  let qry="SELECT max(d.s_no) count FROM stockincnote h, stockincnote_detail d where h.id=d.base_id and h.c_prefix='"+this.stockInCNote.cPrefix+"'";
  this.userService.getValues(qry).subscribe({
    next: (response) => {
      this.stockInCNote.fromNo = JSON.parse(JSON.stringify(response.data.count));
      debugger;
      let count=JSON.parse(JSON.stringify(response.data.count));
       if(count==""){
        this.stockInCNote.fromNo=0;
       }
    },
    error: (error) => {
        alert('There was an error in retrieving data from the server');
    }
});
  //this.stockInCNote.fromNo = 1;
  //this.stockInCNote.toNo = this.stockInCNote.count;
}

calculatate1(event){
 this.stockInCNote.toNo = Number(this.stockInCNote.fromNo)+Number(this.stockInCNote.count);
}

}


