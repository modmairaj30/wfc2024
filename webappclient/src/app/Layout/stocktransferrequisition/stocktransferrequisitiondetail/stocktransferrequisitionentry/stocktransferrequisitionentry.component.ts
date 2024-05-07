import { Component, OnInit, ViewChild, Output, EventEmitter, Input, HostListener } from '@angular/core';
import { StockRequisition, StockRequistionDetail } from 'src/app/layout/domain';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { LookUpFormBean } from 'src/app/layout/domain';
import { TransactionService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';
//@import "src/style/entryscreen.scss";

@Component({
  selector: 'app-stocktransferrequisitionentry',
  templateUrl: './stocktransferrequisitionentry.component.html',//
  styleUrls: ['./stocktransferrequisitionentry.component.scss']
})
export class StocktransferrequisitionentryComponent implements OnInit {

  

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
  public stockRequisition = new StockRequisition();
   //stockVerificationDetailList = new Array<StockVerificationDetail>();
  products2: StockRequistionDetail[] = new Array();
  clonedProducts: { [s: string]: StockRequistionDetail; } = {};

  filteredEmpList1: any[];
  filteredEmpList2: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();

  listLookUpFormBean: LookUpFormBean[] = new Array();
  input: any;

  constructor(private messageService: MessageService,private userService: UserService, private transactionService: TransactionService) { }

  ngOnInit(): void {
    /////list
    this.stockRequisition.userId=window.sessionStorage.getItem("userName");
    this.lookUpFormBean1.query = "select id,location_name,location_code from tbl_location where id in("+sessionStorage.getItem("location")+")";
    this.lookUpFormBean2.query = "SELECT id, prod_name,product_code,author,product_rate, discount, supp_name,supp_id, publisher_name FROM title_view3";

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

            this.stockRequisition.docDt= new Date();
            let location= window.sessionStorage.getItem("location");
         this.stockRequisition.locationId = this.locationList1.find(x => x.id == location.split(",")[0]);

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
      if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredEmpList1 = filtered;
  }

  filterEmpList2(event) {
    let filtered: any[] = [];
    let query = event.query;
    debugger;
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
    this.stockRequisition.stockRequisitionDetailList = this.products2;
    let copy = JSON.parse(JSON.stringify(this.stockRequisition))
    copy.userId=window.sessionStorage.getItem("userName");
    copy.locationId=copy.locationId.id
    /*
    copy.stockVerificationDetailList.forEach((item, indexa) => {
    copy.stockVerificationDetailList[indexa].productId = copy.stockVerificationDetailList[indexa].productId.id;
    });
   */
    this.transactionService.saveStockRequisition(copy).subscribe(
      data => {
        if (data.statusCode == "OK") {
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
  
  editmode() {
   
  }
  reset() {
    window.location.reload();
   // this.stockVerification = new StockVerification();
    //this.stockVerificationDetailList = new Array<StockVerificationDetail>();
  //this.products2 = new Array();
  //this.clonedProducts= {};

  }

  
@HostListener('keydown.F3', ['$event']) F3($event: KeyboardEvent) {
  
}
showSuccess() {
  // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
  this.messageService.add({ key: 'success', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
}
showFail(value:string) {
  // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
  this.messageService.add({ key: 'error', severity: 'error', summary: 'error', detail:value });
}
}
