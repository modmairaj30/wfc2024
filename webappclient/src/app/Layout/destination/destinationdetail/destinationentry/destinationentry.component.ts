import { Component, OnInit, Input } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { Destinationmaster } from 'src/app/Layout/domain/destinationmaster';
import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-destinationentry',
  templateUrl: './destinationentry.component.html',
  styleUrls: ['./destinationentry.component.scss']
})
export class DestinationentryComponent implements OnInit {

  @Input()
  destinationmaster = new Destinationmaster();
  parentlocationList3: any[] = [];
  localList2: any[] = [];
  empList1: any[] = [];

  map = new Map();


  filteredEmpList1: any[];
  filteredEmpList2: any[];
  filteredParentlocationList3: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();
  lookUpFormBean3 = new LookUpFormBean();
  listLookUpFormBean: LookUpFormBean[] = new Array();


  constructor(private userService: UserService, private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {
debugger;

    /////list

    this.lookUpFormBean1.query = "SELECT id,location_code,location_name FROM tbl_location";//branch
    this.lookUpFormBean2.query = "SELECT id,location_code,location_name FROM Location"; //Zone
    this.lookUpFormBean3.query = "SELECT id,parent_name FROM parent_location";

    this.lookUpFormBean1.listName = "LIST1";
    this.lookUpFormBean2.listName = "LIST2";
    this.lookUpFormBean3.listName = "LIST3";

    this.listLookUpFormBean.push(this.lookUpFormBean1);
    this.listLookUpFormBean.push(this.lookUpFormBean2);
    this.listLookUpFormBean.push(this.lookUpFormBean3);

    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.empList1 = JSON.parse(JSON.stringify(data.data.LIST1));
        this.localList2 = JSON.parse(JSON.stringify(data.data.LIST2));
        this.parentlocationList3 = JSON.parse(JSON.stringify(data.data.LIST3));

        this.destinationmaster.state_code=0.0;
        
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
      if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredEmpList1 = filtered;
  }

  filterEmpList2(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.localList2.length; i++) {
      let item = this.localList2[i];
      if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
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
      if (item.parent_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredParentlocationList3 = filtered;
  }



  save() {
    this.destinationmaster.branch = this.destinationmaster.branch.location_name;
    //this.destinationmaster.zone = this.destinationmaster.zone.id;
   // this.destinationmaster.parent_local = this.destinationmaster.parent_local.id;
    this.masterService.saveDestinationMaster(this.destinationmaster).subscribe(
      data => {
        this.showSuccess();
        // alert(data.data);
      },
      err => {
        this.map = JSON.parse(err.error).message;
      }
    );
  }
  showSuccess() {
    // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
    this.messageService.add({ key: 'bc', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }

}
