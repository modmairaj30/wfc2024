import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MessageService } from 'primeng/api';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { StockVerification, StockVerificationDetail } from 'src/app/layout/domain';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { MasterService } from 'src/app/layout/service';
import { read, utils } from 'xlsx';

@Component({
  selector: 'app-productionentry',
  templateUrl: './productionentry.component.html',//
  styleUrls: ['./productionentry.component.scss']
})
export class ProductionentryComponent implements OnInit {

  @ViewChild('detailtable') detailtable;

  @Input()
  public stockVerification = new StockVerification();
   //stockVerificationDetailList = new Array<StockVerificationDetail>();
  products2: StockVerificationDetail[] = new Array();
  clonedProducts: { [s: string]: StockVerificationDetail; } = {};


  dateValue: Date;
  sno: number = 0;
  values = [];
  parentlocationList3: any[] = [];
  localList2: any[] = [];
  empList1: any[] = [];
  localList4: any[] = [];
  localList5: any[] = [];
  localList6: any[] = [];
  map = new Map();

  filteredEmpList1: any[];
  filteredEmpList2: any[];
  filteredParentlocationList3: any[];
  filteredList5: any[];
  filteredList6: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();
  lookUpFormBean3 = new LookUpFormBean();
  lookUpFormBean4 = new LookUpFormBean();
  lookUpFormBean5 = new LookUpFormBean();
  lookUpFormBean6 = new LookUpFormBean();
  listLookUpFormBean: LookUpFormBean[] = new Array();
  selectedTable: any = null;
  file: any = null;
  localTableDropDown: Array<any> = [];
  filterDropDown: Array<any> = [];
  selectedFile: any = null;

  constructor(private userService: UserService, private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.loadData();


    // Old Code for Production Start
    /////list
    this.lookUpFormBean1.query = "select id,type from tariff_type_master";
    this.lookUpFormBean2.query = "SELECT id, prod_name,product_code,author,product_rate, discount, supp_name, publisher_name FROM title_view";
    this.lookUpFormBean3.query = " select id,party_name from tbl_party_info where party_name<>''";
    this.lookUpFormBean4.query = "Select id, card_name from card_type";
    this.lookUpFormBean5.query = "select id,location_name from tbl_location";
    this.lookUpFormBean6.query = "select id,location_code,location_name from location";

    this.lookUpFormBean1.listName = "LIST1";
    this.lookUpFormBean2.listName = "LIST2";
    this.lookUpFormBean3.listName = "LIST3";
    this.lookUpFormBean4.listName = "LIST4";
    this.lookUpFormBean5.listName = "LIST5";
    this.lookUpFormBean6.listName = "LIST6";

    this.listLookUpFormBean.push(this.lookUpFormBean1);
    this.listLookUpFormBean.push(this.lookUpFormBean2);
    this.listLookUpFormBean.push(this.lookUpFormBean3);
    this.listLookUpFormBean.push(this.lookUpFormBean4);
    this.listLookUpFormBean.push(this.lookUpFormBean5);
    this.listLookUpFormBean.push(this.lookUpFormBean6);


    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.empList1 = JSON.parse(JSON.stringify(data.data.LIST1));
        this.localList2 = JSON.parse(JSON.stringify(data.data.LIST2));
        this.parentlocationList3 = JSON.parse(JSON.stringify(data.data.LIST3));

        this.localList4 = JSON.parse(JSON.stringify(data.data.LIST4));
        this.localList5 = JSON.parse(JSON.stringify(data.data.LIST5));
        this.localList6 = JSON.parse(JSON.stringify(data.data.LIST6));
        
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
      if (item.type.toLowerCase().indexOf(query.toLowerCase()) == 0) {
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
   
  }

  public checkSelectOptionValue(value) {
    if (value == "undefined" || value == null) {
      return value;
    }
  }

  
  // Old Production Code End

  selectFile(e: any) {
    if (e.target.files) {
      let selectFile = e.target.files;
      this.selectedFile = selectFile[0];
    }
  }
  
  uploadFile(){
    let formData = new FormData();
    formData.append("file", this.selectedFile);
    formData.append("table", this.selectedTable.name);
    this.masterService.saveExcelData(formData).subscribe(
      (data:any) => {
       this.showSuccess(data)
      },
      (error)=>{
        //error
        console.log(error);
        
      }
    );
  }

  loadData() {
    this.masterService.getDropdownTblExcelUpload().subscribe(res => {
      if (res.data) {
        this.localTableDropDown = res.data;
      }
    })
  }

  filterDropdownList(event) {
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.localTableDropDown.length; i++) {
      let item = this.localTableDropDown[i];
      if (item.name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filterDropDown = filtered;
  }

  showSuccess(message: string) {
    // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
    this.messageService.add({ key: 'bc', severity: 'success', summary: 'success', detail: message });
  }


}


