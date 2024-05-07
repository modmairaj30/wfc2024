import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { Stockincnotemaster } from 'src/app/Layout/domain/stockincnote';
import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-stockincnotemasterentry',
  templateUrl: './stockincnotemasterentry.component.html',
  styleUrls: ['./stockincnotemasterentry.component.scss']
})
export class StockincnotemasterentryComponent implements OnInit {
  dateValue: Date;
  values = [];
  parentlocationList3: any[] = [];
  localList2: any[] = [];
  empList1: any[] = [];

  map = new Map();
  public stockincnotemaster = new Stockincnotemaster();

  filteredEmpList1: any[];
  filteredParentlocationList3: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();
  lookUpFormBean3 = new LookUpFormBean();
  listLookUpFormBean: LookUpFormBean[] = new Array();


  constructor(private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {

    //this.branchmaster.localOS={destination: "Palasa"};

    /////list

    this.lookUpFormBean1.query = "SELECT id,emp_name FROM emp_master";
    this.lookUpFormBean2.query = "SELECT id,local_OS FROM local_OS";
    this.lookUpFormBean3.query = "SELECT id,location_name as parent_name FROM tbl_location";

    this.lookUpFormBean1.listName = "LIST1";
    this.lookUpFormBean2.listName = "LIST2";
    this.lookUpFormBean3.listName = "LIST3";

    this.listLookUpFormBean.push(this.lookUpFormBean1); 
    this.listLookUpFormBean.push(this.lookUpFormBean2);
    this.listLookUpFormBean.push(this.lookUpFormBean3);

    this.masterService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.empList1 = JSON.parse(JSON.stringify(data.data.LIST1));
        this.localList2 = JSON.parse(JSON.stringify(data.data.LIST2));
        this.parentlocationList3 = JSON.parse(JSON.stringify(data.data.LIST3));
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
      if (item.emp_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredEmpList1 = filtered;
  }

  filterParentlocationList3(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.parentlocationList3.length; i++) {
      let item = this.parentlocationList3[i];
      if (item.parent_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredParentlocationList3 = filtered;
  }


  save() {
    this.masterService.saveBranchMaster(this.stockincnotemaster).subscribe(
      data => {
        this.showSuccess();
        // alert(data.data);
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


  showSuccess() {
    // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
    this.messageService.add({ key: 'bc', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }

}
