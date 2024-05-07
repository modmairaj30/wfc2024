import { Component, OnInit, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';


import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';
import { ListboxModule } from 'primeng/listbox';
import { LookUpFormBean, Partymaster } from 'src/app/layout/domain';
interface Country {
  name: string;
  code: string;
}
@Component({
  selector: 'app-partyentry',
  templateUrl: './partyentry.component.html',
  styleUrls: ['./partyentry.component.scss']
})


export class PartyentryComponent implements OnInit {

  @Output()
  editEntry = new EventEmitter();
  @Input()
  editmod: boolean = false;
  parties: any[] = [];
  
  branches: any[] = [];
  countries: any[];
  map = new Map();
  error: string;
  errorMessage: string
  @Input()
  public partymaster = new Partymaster();
  filteredParties: any[];
  filteredbranches: any[];

  lookUpFormBean3 = new LookUpFormBean();
  listLookUpFormBean: LookUpFormBean[] = new Array();
  lookUpFormBean4 = new LookUpFormBean();
  editmode: any;



  constructor(private userService: UserService, private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {

    /*
    this.lookUpFormBean.query="SELECT max(id)+1 id FROM tbl_party_info";
    this.userService.getLookupValues(this.lookUpFormBean).subscribe(
      data => {
        
        let temp =JSON.parse(JSON.stringify(data.data));
        this.count= parseInt(temp[0].id);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
*/
    /////list


    this.lookUpFormBean3.query = "select id,state_name,state_code from state";
    this.lookUpFormBean3.listName = "LIST3";
    this.listLookUpFormBean.push(this.lookUpFormBean3);
    this.lookUpFormBean4.query = "select id,location_name,location_code,state_code from tbl_location";
    this.lookUpFormBean4.listName = "LIST4";
    this.listLookUpFormBean.push(this.lookUpFormBean4);

    this.countries = [
     
    ];

    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        debugger;
        this.parties = JSON.parse(JSON.stringify(data.data.LIST3));
        this.branches = JSON.parse(JSON.stringify(data.data.LIST4));
        if (this.editmod) {
          this.editpartyMaster();

        
        }
        this.partymaster.fuelSurchargePc=0.0;
        this.partymaster.cgst=0.0;
        this.partymaster.sgst=0.0;
        this.partymaster.igst=0.0;
        this.partymaster.declaration=0.0;
      },
      err => {
        this.map = JSON.parse(err.error).message;
      }
    );

    }

    editpartyMaster() {
      console.log(this.partymaster);
      this.partymaster.branches = [];
      this.partymaster.branches.push(this.branches.find(x => x.id == this.partymaster.branchId));
    }


  filterParty(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.parties.length; i++) {
      let party = this.parties[i];
     // if (party.type_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
      if (party.state_name.toLowerCase().includes(query.toLowerCase())) {
        filtered.push(party);
      }
    }

    this.filteredParties = filtered;
  }

  stateSelection(event) {
    
    debugger;
   this.branches.filter(s => s.state_code === event.state_code)
   this.filteredbranches = this.branches.filter(s => s.state_code === event.state_code);
    
   }

  filterbranch(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.branches.length; i++) {
      let branch = this.branches[i];
     // if (party.type_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
      if (branch.location_name.toLowerCase().includes(query.toLowerCase())) {
        filtered.push(branch);
      }
    }

    this.filteredbranches = filtered;
  }



  save() {
    let copy = JSON.parse(JSON.stringify(this.partymaster))
    copy.partyType = copy.partyType.id;
    console.log(copy);
    this.masterService.savePartyMaster(copy).subscribe(
      data => {
        debugger;
        if (data.statusCode == "OK") {
          this.showSuccess();
        }
        else {
          debugger;
          this.errorMessage = data.webAppException.webAppError[0].reason;
          this.showFail(this.errorMessage);
        }

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
 

  showSuccess() {
    this.messageService.add({ key: 'success', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }
  showFail(value: string) {
    this.messageService.add({ key: 'error', severity: 'error', summary: 'error', detail: value });
  }
  }
