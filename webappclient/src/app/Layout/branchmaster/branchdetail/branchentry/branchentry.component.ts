import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';
import { Branchmaster, LookUpFormBean } from 'src/app/layout/domain';

@Component({
  selector: 'app-branchentry',
  templateUrl: './branchentry.component.html',
  styleUrls: ['./branchentry.component.scss']
})
export class BranchentryComponent implements OnInit {

  parentlocationList3: any[] = [];
  localList2: any[] = [];
  empList1: any[] = [];
  error: string;
  map = new Map();

  errorMessage:string
  //public branchmaster = new Branchmaster();

  @Input()
  branchmaster = new Branchmaster();


  filteredEmpList1: any[];
  filteredParentlocationList3: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();
  lookUpFormBean3 = new LookUpFormBean();
  listLookUpFormBean: LookUpFormBean[] = new Array();


  constructor(private userService: UserService, private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {

    //this.branchmaster.localOS={destination: "Palasa"};

    /////list

    this.lookUpFormBean1.query = "SELECT id,emp_name FROM emp_master";
    this.lookUpFormBean2.query = "SELECT id,local_OS FROM local_OS";
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
        if (this.branchmaster != null) {
          this.editmode();

          this.branchmaster.stateCode=null;

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


   // this.branchmaster.contName = this.branchmaster.contName.id;
     let copy = JSON.parse(JSON.stringify(this.branchmaster))
     copy.parent = copy.parent.id;
    this.masterService.saveBranchMaster(copy).subscribe(
      data => {
        debugger;
        if (data.statusCode == "OK") {
        this.showSuccess();
        }
        else{
          debugger;
          this.errorMessage = data.webAppException.webAppError[0].reason;
          alert(this.errorMessage);
        }
         
        //this.branchmaster = new Branchmaster();
      },
      error => {
        debugger;
        console.error('error caught in component')
          this.errorMessage = error;
          this.error = error;
        //this.loading = false;
        
        this.map = JSON.parse(error.error).message;
      }
    );
  }

  public checkSelectOptionValue(value) {
    if (value == "undefined" || value == null) {
      return value;
    }
  }

  reset() {
    this.branchmaster = new Branchmaster();
  }
  editmode() {

    this.branchmaster.contName = this.empList1.find(x => x.id == this.branchmaster.contName);
    // this.branchmaster.localOS = this.localList2.find(x => x.id == this.branchmaster.localOS);
    this.branchmaster.parent = this.parentlocationList3.find(x => x.id == this.branchmaster.parent);
  }
  showSuccess() {
    // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
    this.messageService.add({ key: 'bc', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }
}
