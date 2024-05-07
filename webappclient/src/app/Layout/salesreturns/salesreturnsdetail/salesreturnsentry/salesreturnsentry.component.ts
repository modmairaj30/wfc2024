import { Component, OnInit, ViewChild, Input, Output, EventEmitter, HostListener, ElementRef } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { MasterService, TransactionService } from 'src/app/layout/service';
import { Salesreturns, SalesreturnsDetails } from 'src/app/layout/domain';
import { Router} from '@angular/router';
import { MessageService } from 'primeng/api';
import { AutoComplete } from 'primeng/autocomplete';
import { NgModel } from '@angular/forms';
import { LocationService } from 'src/app/Layout/service/locationservice';
//@import "src/style/entryscreen.scss";

@Component({
  selector: 'app-salesreturnsentry',
  templateUrl: './salesreturnsentry.component.html',
  styleUrls: ['./salesreturnsentry.component.scss']
})
export class SalesreturnsentryComponent implements OnInit {
  [x: string]: any;


  @ViewChild('detailtable') detailtable;
  @ViewChild('formForm') formForm;
  @ViewChild('fe') input: AutoComplete;
  //@ViewChild('localOS') private localOS: AutoComplete;

  @Output()
  editEntry = new EventEmitter();

  @Output() 
  shareData1 = new EventEmitter();

  @Input()
  shareData: any[] = [];

  

  dateValue: Date;
  @Input()
  public salesreturns = new Salesreturns();
   products2: SalesreturnsDetails[] = new Array();
  clonedProducts: { [s: string]: SalesreturnsDetails; } = {};
  @Input()
  editmod: boolean=false;
  sno: number = 0;
  values = [];
  titleList2: any[] = [];
  locationList1: any[] = [];
  

  map = new Map();
  error: string;
  errorMessage:string

  

  
  parentlocationList3: any[] = [];
  localList2: any[] = [];
  empList1: any[] = [];
  localList4: any[] = [];
  
  localList6: any[] = [];
  
  filteredEmpList1: any[];
  filteredEmpList2: any[];
  filteredParentlocationList3: any[];
  
  filteredList6: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();
  lookUpFormBean3 = new LookUpFormBean();
  lookUpFormBean4 = new LookUpFormBean();
  
  lookUpFormBean6 = new LookUpFormBean();
  listLookUpFormBean: LookUpFormBean[] = new Array();
  

  constructor(private messageService: MessageService, private userService: UserService, private transactionService: TransactionService) { }

  ngOnInit(): void {
    /////list
    this.salesreturns.userId=window.sessionStorage.getItem("userName");
    this.lookUpFormBean1.query = "select id,party_name,mobileno,city,address_1,address_2 from tbl_party_info where party_type=1";
    this.lookUpFormBean2.query = "SELECT id, prod_name,product_code,author,product_rate, discount, supp_name,supp_id, publisher_name FROM title_view3";
    this.lookUpFormBean3.query = " select id,party_name from tbl_party_info where party_name<>''";
    //this.lookUpFormBean4.query = "Select id, card_name from card_type";
    
    this.lookUpFormBean6.query = "select id,location_code,location_name from tbl_location";

    this.lookUpFormBean1.listName = "LIST1";
    this.lookUpFormBean2.listName = "LIST2";
    this.lookUpFormBean3.listName = "LIST3";
    this.lookUpFormBean4.listName = "LIST4";
    
    this.lookUpFormBean6.listName = "LIST6";

    this.listLookUpFormBean.push(this.lookUpFormBean1);
    this.listLookUpFormBean.push(this.lookUpFormBean2);
    this.listLookUpFormBean.push(this.lookUpFormBean3);
    this.listLookUpFormBean.push(this.lookUpFormBean4);
   
    this.listLookUpFormBean.push(this.lookUpFormBean6);


    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.empList1 = JSON.parse(JSON.stringify(data.data.LIST1));
        this.localList2 = JSON.parse(JSON.stringify(data.data.LIST2));
        this.parentlocationList3 = JSON.parse(JSON.stringify(data.data.LIST3));

        //this.localList4 = JSON.parse(JSON.stringify(data.data.LIST4));
        
        this.localList6 = JSON.parse(JSON.stringify(data.data.LIST6));
        this.shareData[0]=this.empList1;
        this.shareData[1]=this.localList6;
        if (this.editmod) {
                  this.editmode();
        }else{
        this.addvalue();
        setTimeout(() => {

        this.input.focusInput();
        }, 1000);

        this.salesreturns.docDt= new Date();
        let location= window.sessionStorage.getItem("location");
         this.salesreturns.locationId = this.localList6.find(x => x.id == location.split(",")[0]);

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
      if (item.party_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredEmpList1 = filtered;
  }

  filterEmpList2(event) {
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.localList2.length; i++) {
      let item = this.empList1[i];
      if (item.prod_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredEmpList2 = filtered;
  }

  filterParentlocationList3(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.parentlocationList3.length; i++) {
      let item = this.parentlocationList3[i];
      if (item.party_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredParentlocationList3 = filtered;
  }

  filterList5(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.localList5.length; i++) {
      let item = this.localList5[i];
      if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredList5 = filtered;
  }


  filterList6(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.localList6.length; i++) {
      let item = this.localList6[i];
      if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredList6 = filtered;
  }

  //saving logics

   save() {
    //this.elRef.focusInput();
    debugger;
    this.salesreturns.invoiceDetList = this.products2;
    
    let copy = JSON.parse(JSON.stringify(this.salesreturns))
    copy.locationId=copy.locationId.id
    //copy.locationCode=copy.locationCode.location_name;
    copy.partyId=copy.partyId.id;
    
   //copy.locationId=copy.locationCode.id
    
    /*copy.salesreturnsDetailList.forEach((item, indexa) => {
    copy.salesInvoiceDetailList[indexa].productId = copy.salesInvoiceDetailList[indexa].productId.id;
    });*/
   
    this.transactionService.saveSalesreturns(copy).subscribe(
      data => {
        if(!data && data == null){
          this.showFail(this.errorMessage);
          return true;
        } 
         if (data.statusCode == "OK") {
          this.showSuccess();
          this.salesreturns.docDt= new Date();
	this.salesreturns.invoiceDetList = this.products2;
          this.salesreturns.docNo = "";
          this.salesreturns.grossAmt = null;
          this.salesreturns.netAmt = null;
          this.salesreturns.address = "";
          this.salesreturns.discAmt = null;
          this.salesreturns.partyId = null;
          this.salesreturns.invoiceDetList=[];
          this.sno = 0;
          this.products2= [];
          this.addvalue();
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
   addvalue() {
    if (this.detailtable.value.length == 0) {
      this.sno = 0;
    }

    this.sno = 1 + this.sno;
    this.products2.push({
      sno: this.sno, id: null, baseId: null, productName: null, productId: null, productCode: null, rate: 0, qty: 0, availQty: 0, difference: null, description: null, author: null, supplier: null, publisher: null,
      fldBin: null, rackNo: null, fldSrate: null, fldDisc: null, totalAmt: null, dummyD: null, Disc: null
    });
    this.detailtable.initRowEdit({
      sno: this.sno, id: null, baseId: null, productName: null, productId: null, productCode: null, rate: 0, qty: 0, availQty: 0, difference: null, description: null, author: null, supplier: null, publisher: null,
      fldBin: null, rackNo: null, fldSrate: null, fldDisc: null, totalAmt: null, dummyD: null
    });
    this.clonedProducts[this.products2[this.sno - 1].sno] = { ...this.products2[this.sno - 1] };
    
    
  }
  onRowEditInit(product: SalesreturnsDetails) {
    this.clonedProducts[product.id] = { ...product };
    
  }

  onRowEditSave(product: SalesreturnsDetails) {
    delete this.clonedProducts[product.id];
  }

  onRowEditCancel(product: SalesreturnsDetails, index: number) {
    this.products2[index] = this.clonedProducts[product.id];
    delete this.clonedProducts[product.id];
  }
  onRowDelete(product: SalesreturnsDetails, index: number) {
    this.detailtable.value.forEach((item, indexa) => {
      if (index == indexa) {
        this.detailtable.value.splice(indexa, 1);
        this.sno--;
      }
      });
    this.detailtable.value.forEach((item, indexa) => {
      item.sno=indexa+1;
    });

  }
  selectedAutoCompletedValue(event, index: number, product: SalesreturnsDetails) {
   
    this.detailtable.value;
    this.products2;
    product.author = event.author;
    product.productId=event.id;
    product.productName = event.prod_name;
    product.productCode = event.product_code;
    product.rate = event.product_rate;
    product.supplier = event.supp_name;
    product.publisher = event.publisher_name;
    product.Disc = event.discount;
    //product.netAmt = (product.qty * product.rate);
    //product.netAmt.toFixed(2);

  }
  change2(i, index: number, product: SalesreturnsDetails) {
    product.amount = (product.qty * product.rate);
    product.amount.toFixed(2);
    
    let grosstotal=0;
    let totaldiscount=0;
    let discount=0;
    let netamounttotal=0;
    let netamt=product.amount;
    
    for( i= 0; i < this.detailtable.value.length; i++){ 
    
         discount =(this.detailtable.value[i].amount*(this.detailtable.value[i].Disc/100));
         this.detailtable.value[i].netAmt=this.detailtable.value[i].amount-discount;
         this.detailtable.value[i].discAmt=discount;
   
         grosstotal= grosstotal + this.detailtable.value[i].amount;
         netamounttotal=netamounttotal+ this.detailtable.value[i].netAmt;
          totaldiscount= totaldiscount+discount;
        
          
      }
        this.salesreturns.netAmt=netamounttotal;
        this.salesreturns.grossAmt=grosstotal;
        this.salesreturns.discAmt = totaldiscount;
       


  }

  change3(event) {
     this.salesreturns.docNo=event.location_code+"-SR-";
    }
  editmode() {
    this.products2=this.salesreturns.invoiceDetList;
    
    this.salesreturns.docDt=new Date(this.salesreturns.docDt);
   this.salesreturns.locationId = this.localList6.find(x => x.id == this.salesreturns.locationId);
   this.salesreturns.partyId = this.empList1.find(x => x.id == this.salesreturns.partyId);
   for(let i = 0; i < this.salesreturns.invoiceDetList.length; i++){
    this.salesreturns.invoiceDetList[i].sno = i+1; 
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
    this.salesreturns = new Salesreturns();
    this.products2= new Array();
    this.clonedProducts={};
    this.sno = 0;
    this.addvalue();
        setTimeout(() => {
          if (this.editmod) {
          //  this.editmod=false;
            //  this.reset();
            // this.router.navigate(['/purchaseInvoice/purchaseInvoice']);
            this.editEntry.emit(this.editmod);
          }
          else{
            this.input.focusInput();
          }
        }, 1500);
    
        

  }
@HostListener('keydown.F7', ['$event']) F7($event: KeyboardEvent) {
  this.addvalue();
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

}
