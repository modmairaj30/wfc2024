import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { Credittarriffmaster, CredittarriffmasterDetail, LookUpFormBean } from 'src/app/layout/domain';
import { MasterService } from 'src/app/layout/service';

@Component({
  selector: 'app-credittrafficmasterentry',
  templateUrl: './credittrafficmasterentry.component.html',
  styleUrls: ['./credittrafficmasterentry.component.scss']

})
export class CredittrafficmasterentryComponent implements OnInit {

  @ViewChild('detailtable') detailtable;
  
  sno: number = 0;
  count: number = 0;
  content: any[] = [];
  dateValue: Date;
   parentlocationList3: any[] = [];
  localList2: any[] = [];
  empList1: any[] = [];
  localList4: any[] = [];
  localList5: any[] = [];
  localList6: any[] = [];
  localList7: any[] = [];
  map = new Map();
  cloneMap = new Map();
  @Input()
  public credittarriffmaster = new Credittarriffmaster();
  @Input()
  editmod: boolean = false;
  products2: CredittarriffmasterDetail[] = new Array();
  clonedProducts: { [s: string]: CredittarriffmasterDetail; } = {};

  filteredEmpList1: any[];
  filteredParentlocationList3: any[];
  filteredList5: any[];
  filteredList6: any[];
  filteredList7: any[];

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();
  lookUpFormBean3 = new LookUpFormBean();
  lookUpFormBean4 = new LookUpFormBean();
  lookUpFormBean5 = new LookUpFormBean();
  lookUpFormBean6 = new LookUpFormBean();
  lookUpFormBean7 = new LookUpFormBean();
  listLookUpFormBean: LookUpFormBean[] = new Array();
  branches: any;
  filteredbranches: any;
  countries: any[];

  constructor(private userService: UserService, private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {

    this.credittarriffmaster.userId = sessionStorage.getItem("userName");
    /////list
    this.lookUpFormBean1.query = "select id,type from tariff_type_master ";
    this.lookUpFormBean2.query = "Select id, Modeofconsignment from mode";
    this.lookUpFormBean3.query = " select id,party_name, branch from tbl_party_info where party_name<>''";
    this.lookUpFormBean4.query = "Select id, card_name from card_type";
    this.lookUpFormBean5.query = "select id,location_name,state_code from tbl_location";
    this.lookUpFormBean6.query = "select id,location_code,location_name from location";
    this.lookUpFormBean7.query = "select id,state_name,state_code from state";

    this.lookUpFormBean1.listName = "LIST1";
    this.lookUpFormBean2.listName = "LIST2";
    this.lookUpFormBean3.listName = "LIST3";
    this.lookUpFormBean4.listName = "LIST4";
    this.lookUpFormBean5.listName = "LIST5";
    this.lookUpFormBean6.listName = "LIST6";
    this.lookUpFormBean7.listName = "LIST7";

    this.listLookUpFormBean.push(this.lookUpFormBean1);
    this.listLookUpFormBean.push(this.lookUpFormBean2);
    this.listLookUpFormBean.push(this.lookUpFormBean3);
    this.listLookUpFormBean.push(this.lookUpFormBean4);
    this.listLookUpFormBean.push(this.lookUpFormBean5);
    this.listLookUpFormBean.push(this.lookUpFormBean6);
    this.listLookUpFormBean.push(this.lookUpFormBean7);

    this.countries = [
     
    ];
    



    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.empList1 = JSON.parse(JSON.stringify(data.data.LIST1));
        this.localList2 = JSON.parse(JSON.stringify(data.data.LIST2));
        this.parentlocationList3 = JSON.parse(JSON.stringify(data.data.LIST3));

        this.localList4 = JSON.parse(JSON.stringify(data.data.LIST4));
        this.localList5 = JSON.parse(JSON.stringify(data.data.LIST5));
        this.localList6 = JSON.parse(JSON.stringify(data.data.LIST6));
        this.localList7 = JSON.parse(JSON.stringify(data.data.LIST7));
        if (this.editmod) {
          this.editmode();
        } else {
        
          debugger;
          this.credittarriffmaster.modeConsign=2;
          this.credittarriffmaster.paymentType=1;
          this.credittarriffmaster.docDt = new Date();
          this.credittarriffmaster.docTime= new Date();
          this.credittarriffmaster.toWeight=0.0;
          this.credittarriffmaster.fromWeight=0.0;
          

          //let location= window.sessionStorage.getItem("location");
         this.credittarriffmaster.type = this.empList1[0];
        

          this.addvalue();
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
      if (item.type.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredEmpList1 = filtered;
  }

  filterParentlocationList3(event) {

    let filtered: any[] = [];
    let query = event.query;
   // let branch: any = this.credittarriffmaster.locationId;
   // if(branch){
      for (let i = 0; i < this.parentlocationList3.length; i++) {
        let item = this.parentlocationList3[i];
      //  if (item.party_name.toLowerCase().indexOf(query.toLowerCase()) == 0 && item.branch==branch.id) {
          filtered.push(item);
       // }
      }
        this.filteredParentlocationList3 = filtered;
   // }else {
    //  this.messageService.add({ key: 'bc', severity: 'error', summary: 'error', detail: 'Please select Branch first.' });
  //  }
   
  }

  filterList5(event) {

    let filtered: any[] = [];
    let query = event.query;
    let state: any = this.credittarriffmaster.state_code;
    if (state) {
      for (let i = 0; i < this.localList5.length; i++) {
        let item = this.localList5[i];
        if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0 && item.state_code == state.state_code) {
          filtered.push(item);
        }
      }
      this.filteredList5 = filtered;
   } else {
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'error', detail: 'Please select State first.' });
    }
  
  }
  stateSelection(event) {
       
   this.localList5.filter(s => s.state_code === event.state_code)
   this.filteredList5 = this.localList5.filter(s => s.state_code === event.state_code);
   if (this.editmod) {
     this.credittarriffmaster.locationIds=[this.credittarriffmaster.locationId];
   }
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

  filterList7(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.localList7.length; i++) {
      let item = this.localList7[i];
      if (item.state_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filteredList7 = filtered;
  }

 

  public checkSelectOptionValue(value) {
    if (value == "undefined" || value == null) {
      return value;
    }
  }

   addvalue() {
    debugger;
    if (this.detailtable.value.length == 0) {
      this.sno = 0;
    }
    this.sno = 1 + this.sno;
    this.products2.push(
      { sno: this.sno, id: null, baseId: null, locationId: '', rate: 0.0, addrate: 0.0, addqty: 1 });
    this.detailtable.initRowEdit(
      { sno: this.sno, id: null, baseId: null, locationId: '', rate: 0.0, addrate: 0.0, addqty: 1 });

    this.clonedProducts[this.products2[this.sno - 1].sno] = { ...this.products2[this.sno - 1] };
    

  }


  onRowEditInit(product: Credittarriffmaster, index: number) {
    this.clonedProducts[product.id] = { ...product };
  }

  onRowEditSave(product: Credittarriffmaster, index: number) {
    delete this.clonedProducts[product.id];
  }

  onRowEditCancel(product: Credittarriffmaster, index: number) {
    this.products2[index] = this.clonedProducts[product.id];
    delete this.clonedProducts[product.id];
  }

  onRowDelete(product: Credittarriffmaster, index: number) {
    this.detailtable.value.forEach((item, indexa) => {
      if (index == indexa) {
        this.detailtable.value.splice(indexa, 1);
        this.sno--;
      }
    });

  }

  editmode() {
    this.products2 = this.credittarriffmaster.creditTeriffmasterDetailFormBeanList;
    debugger;
    
    
    this.credittarriffmaster.locationId = this.localList5.find(x => x.id == this.credittarriffmaster.locationId);
    this.credittarriffmaster.partyId = this.parentlocationList3.find(x => x.id == this.credittarriffmaster.partyId);
    this.credittarriffmaster.type = this.empList1.find(x => x.id == this.credittarriffmaster.type);
    this.credittarriffmaster.state = this.localList7.find(x => x.state_code == this.credittarriffmaster.state);
    this.stateSelection(this.credittarriffmaster.state);
   // this.credittarriffmaster.locationIds = this.localList5.find(x => x.location_name == this.credittarriffmaster.locationIds);
    for (let i = 0; i < this.credittarriffmaster.creditTeriffmasterDetailFormBeanList.length; i++) {
      this.credittarriffmaster.creditTeriffmasterDetailFormBeanList[i].sno = i + 1;
      this.credittarriffmaster.creditTeriffmasterDetailFormBeanList[i].locationId = this.localList6.find(x => x.id == this.credittarriffmaster.creditTeriffmasterDetailFormBeanList[i].locationId);
      
      this.sno = i + 1;
    }

  } 
  validate(){
    let zero=0; 
    if(Number(this.credittarriffmaster.fromWeight)<=zero){
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'error', detail: 'Enter value for fromWeight' });
       return 1;
    }
    if(Number(this.credittarriffmaster.toWeight)<=zero){
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'error', detail: 'Enter value for toWeight' });
      return 1;
    }
    
    if(Number(this.credittarriffmaster.toWeight)<Number(this.credittarriffmaster.fromWeight)){
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'error', detail: 'toWeight  can not be less than fromWeight' });
      return 1;
    }
    
    this.products2.forEach((item, indexa) => {
          if(this.products2[indexa].id==null){
        this.messageService.add({ key: 'bc', severity: 'error', summary: 'error', detail: 'Please Select Location' });
        return 1;
      }
    });
   for(let i=0;i>this.products2.length;i++){
    if(this.products2[i].id==null){
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'error', detail: 'Please Select Location' });
      return 1;
    }
   } 
   
    this.save();
  }
  save() {
    debugger;
    //this.partymaster.userId=window.sessionStorage.getItem("userName");
 
   
    this.credittarriffmaster.creditTeriffmasterDetailFormBeanList = this.products2;

    // let partymasterCopy = Object.assign({}, this.partymaster);
    let copy = JSON.parse(JSON.stringify(this.credittarriffmaster))
    copy.type = copy.type.id;
    //copy.locationId = copy.locationId.id;
    for(let i=0;i< copy.locationIds.length;i++){
      copy.locationIds[i]=copy.locationIds[i].id;
    }

    copy.partyId = copy.partyId.id;
    copy.state = copy.state.state_code;
    copy.creditTeriffmasterDetailFormBeanList.forEach((item, indexa) => {
      copy.creditTeriffmasterDetailFormBeanList[indexa].locationId = this.products2[indexa].locationId.id;
      
    });


    this.masterService.saveCreditteriffMaster(copy).subscribe(
      data => {
        this.showSuccess();
        //  alert(data.data);
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