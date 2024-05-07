import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MessageService } from 'primeng/api';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { Approvedmaster } from 'src/app/Layout/domain/Approvedmaster.model';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { ReportService } from 'src/app/Layout/service/reportservice';
import { Credittarriffmaster, Invoice, ReportInput } from 'src/app/layout/domain';
import { MasterService, TransactionService } from 'src/app/layout/service';
//@import "src/style/entryscreen.scss";

@Component({
  selector: 'app-billingprintentry',
  templateUrl: './billingprintentry.component.html',//
  styleUrls: ['./billingprintentry.component.scss']
})
export class BillingprintentryComponent implements OnInit {

  @ViewChild('detailtable') detailtable;

  @Input()
  invoice = new Invoice();
  @Input()
  editmod: boolean = false;
  public reportInput = new ReportInput();

  dateValue: Date;
  loading: boolean = false;

  party_idList: any[] = [];
  card_typeList: any[] = [];
  dest_branchList: any[] = [];
  consignee_destList: any[] = [];
  teriff_locationList: any[] = [];
  statusList: any[] = [];
  location_idList: any[] = [];
  tariff_idList: any[] = [];
  orgin_fldList: any[] = [];
  typeList: any[] = [];
  batchNoList: any[] = [];

  filter_fld_name: any[];
  filter_party_id: any[];
  filter_card_type: any[];
  filter_dest_branch: any[];
  filter_consignee_dest: any[];
  filter_teriff_location: any[];
  filter_status: any[];
  filter_location_id: any[];
  filter_tariff_id: any[];
  filter_orgin_fld: any[];
  filter_type: any[];
  filter_batch_no: any[];


  map = new Map();
  //public branchmaster = new Branchmaster();

  lookUpFormBean1 = new LookUpFormBean();
  lookUpFormBean2 = new LookUpFormBean();
  lookUpFormBean3 = new LookUpFormBean();
  lookUpFormBean4 = new LookUpFormBean();
  lookUpFormBean5 = new LookUpFormBean();
  lookUpFormBean6 = new LookUpFormBean();
  lookUpFormBean7 = new LookUpFormBean();
  lookUpFormBean8 = new LookUpFormBean();
  lookUpFormBean9 = new LookUpFormBean();
  lookUpFormBean10 = new LookUpFormBean();
  lookUpFormBean11 = new LookUpFormBean();  
  listLookUpFormBean: LookUpFormBean[] = new Array();
  defaultDate: Date = null;

  constructor(private userService: UserService,private reportService: ReportService,private masterService: MasterService, private transactionService: TransactionService, private messageService: MessageService) { }

  ngOnInit(): void {
    let currentDate = new Date();
    this.defaultDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()-1)
    this.lookUpFormBean1.query = " SELECT	id,	party_name,Party_label,Party_Code,dec_pers,fuel_surcharge_pc,edu_cess,service_tax_pc,SHE_cess,address_1,mobileno,email_id,phno, cgst,sgst,igst,declaration,customer_gstno,customer_panno	FROM	tbl_party_info where party_type=1";
    this.lookUpFormBean2.query = " SELECT	id,	card_name	FROM	card_type	";
    this.lookUpFormBean3.query = " SELECT	id,	Modeofconsignment	FROM	mode	";
    this.lookUpFormBean4.query = " SELECT	id,	destination,branch,parent_local,state_code	FROM	branch_master	";
    this.lookUpFormBean5.query = " SELECT	id,	location_name	FROM	location	";
    this.lookUpFormBean6.query = " SELECT	id,	status	FROM	status	";
    this.lookUpFormBean7.query = " SELECT	id,	location_name,location_code,state_code	FROM	tbl_location	";
    this.lookUpFormBean8.query = " SELECT	id,	type	FROM	tariff_type_master	";
    this.lookUpFormBean9.query = " SELECT	id,	destination,dest_code	FROM	branch_master	";
    this.lookUpFormBean10.query = " SELECT id, travelling_type	FROM	transaction_type ";
    // this.lookUpFormBean11.query = " SELECT batch_no,cum_qty	FROM	tbl_stock_info where cum_qty>0 and last_row='TRUE' and location_id="+this.invoice.locationId;
   // this.lookUpFormBean11.query = " SELECT batch_no,cum_qty,prefix,location_id	FROM	tbl_stock_info where last_row='TRUE'";

    this.lookUpFormBean1.listName = "LIST1";
    this.lookUpFormBean2.listName = "LIST2";
    this.lookUpFormBean3.listName = "LIST3";
    this.lookUpFormBean4.listName = "LIST4";
    this.lookUpFormBean5.listName = "LIST5";
    this.lookUpFormBean6.listName = "LIST6";
    this.lookUpFormBean7.listName = "LIST7";
    this.lookUpFormBean8.listName = "LIST8";
    this.lookUpFormBean9.listName = "LIST9";
    this.lookUpFormBean10.listName = "LIST10";
    //this.lookUpFormBean11.listName = "LIST11";

    this.listLookUpFormBean.push(this.lookUpFormBean1);
    this.listLookUpFormBean.push(this.lookUpFormBean2);
    this.listLookUpFormBean.push(this.lookUpFormBean3);
    this.listLookUpFormBean.push(this.lookUpFormBean4);
    this.listLookUpFormBean.push(this.lookUpFormBean5);
    this.listLookUpFormBean.push(this.lookUpFormBean6);
    this.listLookUpFormBean.push(this.lookUpFormBean7);
    this.listLookUpFormBean.push(this.lookUpFormBean8);
    this.listLookUpFormBean.push(this.lookUpFormBean9);
    this.listLookUpFormBean.push(this.lookUpFormBean10);
   //this.listLookUpFormBean.push(this.lookUpFormBean11);
    this.loading = true;
    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.loading = false;
        this.party_idList = JSON.parse(JSON.stringify(data.data.LIST1));
        this.card_typeList = JSON.parse(JSON.stringify(data.data.LIST2));
        this.dest_branchList = JSON.parse(JSON.stringify(data.data.LIST3));
        this.consignee_destList = JSON.parse(JSON.stringify(data.data.LIST4));
        this.teriff_locationList = JSON.parse(JSON.stringify(data.data.LIST5));
        this.statusList = JSON.parse(JSON.stringify(data.data.LIST6));
        this.location_idList = JSON.parse(JSON.stringify(data.data.LIST7));
        this.tariff_idList = JSON.parse(JSON.stringify(data.data.LIST8));
        this.orgin_fldList = JSON.parse(JSON.stringify(data.data.LIST9));
        this.typeList = JSON.parse(JSON.stringify(data.data.LIST10));
        //this.batchNoList = JSON.parse(JSON.stringify(data.data.LIST11));
        
        if (this.editmod == true) {
         this.editmode();
        }
        else{
          this.invoice.destBranch = "2";
          this.invoice.type= "1";
          this.invoice.status= "8";
        }
      },
      err => {
        this.loading = false;
        this.map = JSON.parse(err.error).message;
      }
    );

  }

  ///new lookups code 

  calculatate(event){
    this.invoice.decamt = Number(this.invoice.decVal*this.invoice.decPer/100);
    this.invoice.servAmt = Number(((this.invoice.decamt+this.invoice.odCharges+this.invoice.amount+this.invoice.fuelAmt)* this.invoice.servTax)/100);
      // this.invoice.netAmt = Number(this.invoice.amount+this.invoice.decamt+this.invoice.cgstAmt+this.invoice.sgstAmt+this.invoice.fuelAmt+this.invoice.odCharges);
      this.invoice.fuelAmt = Number(((this.invoice.decamt+this.invoice.odCharges+this.invoice.amount)*this.invoice.fuelTax)/100);
      this.invoice.odCharges= Number(this.invoice.odCharges);
     
     this.invoice.cgstAmt = Number((this.invoice.amount+this.invoice.fuelAmt+this.invoice.decamt+this.invoice.odCharges)/100);
     this.invoice.sgstAmt = Number((this.invoice.amount+this.invoice.fuelAmt+this.invoice.decamt+this.invoice.odCharges)/100);
     this.invoice.igstAmt = Number((this.invoice.amount+this.invoice.fuelAmt+ this.invoice.decamt+this.invoice.odCharges)*this.invoice.igst)/100;
     this.invoice.netAmt= this.invoice.amount+this.invoice.decamt+this.invoice.cgstAmt+this.invoice.sgstAmt+this.invoice.fuelAmt+Number(this.invoice.odCharges);
     this.invoice.rptNetamt = Number(this.invoice.decamt+this.invoice.amount+this.invoice.fuelAmt)+Number(this.invoice.odCharges);
  }
  filterBatchNo(event) {
    let filtered: any[] = [];
    
    for (let i = 0; i < this.batchNoList.length; i++) {
      let item = this.batchNoList[i];
      
        filtered.push(item);
      
    }
    this.filter_batch_no = filtered;
  }
  dependentLookupBatchNo(event){
    debugger;
    this.invoice.batchNo='';
    this.invoice.sourceStn=event.id;
    this.invoice.source_st=event.state_code;
    this.invoice.locationCode = event.location_code;
    let filtered: any[] = [];
    this.lookUpFormBean11.query = " SELECT id,batch_no,cum_qty	FROM	tbl_stock_info where cum_qty>0 and  last_row='TRUE' and location_id="+event.id;
    this.loading = true;
    this.userService.getLookupValues(this.lookUpFormBean11).subscribe(
      data => {
        this.loading = false;
        this.batchNoList = JSON.parse(JSON.stringify(data.data));
        for (let i = 0; i < this.batchNoList.length; i++) {
          let item = this.batchNoList[i];
           filtered.push(item);
          
        }
        this.filter_batch_no = filtered;
      },
      err => {
        this.loading = false;
        this.map = JSON.parse(err.error).message;
      }
    );

  }

  terrifCalculation(){
    debugger;
   let  ct = new Credittarriffmaster();
    ct.partyId=this.invoice.partyId.id;
    ct.locationId= this.invoice.teriffLocation.id;
    ct.fromWeight= this.invoice.weight;
    ct.paymentType = Number(this.invoice.cardType);
    ct.type = this.invoice.type;
    ct.modeConsign = Number(this.invoice.destBranch);
    //let partyId: any = this.invoice.partyId;
    //let locationId: any = this.invoice.locationId;
    //let query = `select m.id, m.from_weight, m.to_weight, m.party_id, m.mode_consign, d.base_id, d.rate, d.Add_qty, d.Add_rate from credit_teriffmaster m , credit_teriffmaster_detail d where m.id = d.base_id and  m.party_id = ${partyId.id} and m.location_id = ${locationId.id} and d.location_id = ${locationId.id} and (${this.invoice.weight} > m.from_weight and ${this.invoice.weight} < m.to_weight)`;
    this.loading = true;
    this.masterService.conditionSlabs(ct).subscribe(
      data => {
        this.loading = false;
        if (data.data)
        this.invoice.amount = Number(data.data);
        this.invoice.decamt = Number(this.invoice.decVal*this.invoice.decPer/100);
        this.invoice.servAmt = Number(((this.invoice.decamt+this.invoice.odCharges+this.invoice.amount+this.invoice.fuelAmt)* this.invoice.servTax)/100);
          // this.invoice.netAmt = Number(this.invoice.amount+this.invoice.decamt+this.invoice.cgstAmt+this.invoice.sgstAmt+this.invoice.fuelAmt+this.invoice.odCharges);
          this.invoice.fuelAmt = Number(((this.invoice.decamt+this.invoice.odCharges+this.invoice.amount)*this.invoice.fuelTax)/100);
         this.invoice.netAmt= this.invoice.amount+this.invoice.decamt+this.invoice.cgstAmt+this.invoice.sgstAmt+this.invoice.fuelAmt+this.invoice.odCharges;
      },
      err => {
        this.loading = false;
        this.map = JSON.parse(err.error).message;

      }
    );

  }


  filterLocationId(event) {
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.location_idList.length; i++) {
      let item = this.location_idList[i];
      if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }
    this.filter_location_id = filtered;
  }
  locationIdFromlookup(event) {
    //this.invoice.partyId=event.id;
    debugger;
    this.invoice.locationId  = "";
    this.invoice.sourceStn = event.source_stn;
    this.invoice.locationId = event.id;
    
    // this.dependentLookupBatchNo(event.id);
   }
  consignmenNameFiltered(event) {
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.party_idList.length; i++) {
      let item = this.party_idList[i];
      if (item.party_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }
    this.filter_party_id = filtered;
  }
  partyFromlookup(event) {
    debugger;

    //this.invoice.partyId=event.id;
    this.invoice.address = event.address_1;
    this.invoice.consignerMobNo = event.mobileno;
    this.invoice.consignerEmailId = event.email_id;
    this.invoice.decPer = event.dec_pers;
    //this.invoice.decVal = event.dec_val;
    this.invoice.partyLabel = event.Party_label;
    // this.invoice.amount = event.service_tax_pc;
    this.invoice.fuelTax = event.fuel_surcharge_pc;
    this.invoice.edcessPer = event.edu_cess;
    this.invoice.shessPer = event.SHE_cess;
    this.invoice.consignerEmailId = event.email_id;

    this.invoice.cgstDummy = event.cgst;
    this.invoice.sgstDummy = event.sgst;
    this.invoice.igstDummy = event.igst;

    this.invoice.cgst = event.cgst;
    this.invoice.sgst = event.sgst;
    this.invoice.igst = event.igst;
    //this.invoice.decPer = event.declaration;
    this.invoice.tariffId = event.type;
    //Dec_amt=dec_val_TM_Invoice_TM_b*dec_per_TM_Invoice_TM_b/100
    this.invoice.decamt = Number((this.invoice.decPer*this.invoice.decVal)/100);

   // this.calculateGSTAmount();
  }

    

  

  consigneeDestFromlookup(event) {
    this.invoice.time = event.state_code;
    //this.invoice.consigneeDest = event.id;
    this.invoice.branchForwarding = event.branch;

  }
  
  filterOrigin(event) {
    //
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.orgin_fldList.length; i++) {
      let item = this.orgin_fldList[i];
      if (item.destination.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filter_orgin_fld = filtered;
  }

  filterCardType(event) {
    //
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.card_typeList.length; i++) {
      let item = this.card_typeList[i];
      if (item.card_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filter_card_type = filtered;
  }

  filterDestBranch(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.dest_branchList.length; i++) {
      let item = this.dest_branchList[i];
      if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }
    this.dest_branchList = filtered;
  }

  filterStatus(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.statusList.length; i++) {
      let item = this.statusList[i];
      if (item.status.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }

    this.filter_status = filtered;
  }



  filterConsigneeDest(event) {

    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.consignee_destList.length; i++) {
      let item = this.consignee_destList[i];
      if (item.destination.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(item);
      }
    }
   // if (filtered.length ==0)
     // alert(`The destination '${query}' does not exist, please contact to admin`);
      //this.showError("The destination does not exist, please contact to admin");

    this.consignee_destList = filtered;
  }

  public checkSelectOptionValue(value) {
    if (value == "undefined" || value == null) {
      return value;
    }
  }

  selectCNote(event: any) {
    debugger;
    this.invoice.refNo = event.batch_no;
  }
  
  editmode() {
      this.invoice.locationId = this.location_idList.find(x => x.id == this.invoice.locationId);
      this.invoice.partyId = this.party_idList.find(x => x.id == this.invoice.partyId);
      this.invoice.consigneeDest = this.consignee_destList.find(x => x.id == this.invoice.consigneeDest);
      this.invoice.teriffLocation = this.consignee_destList.find(x => x.id == this.invoice.teriffLocation);

      this.invoice.orginFld = this.orgin_fldList.find(x => x.id == this.invoice.orginFld);
    //  this.invoice.partyId = this.dest_branchList.find(x => x.id == this.invoice.partyId);
      
    
  }
  save(item: any) {
    this.loading = true;
    this.calculatate(event);
    //this.partymaster.userId=window.sessionStorage.getItem("userName");
    this.invoice.batchNo2=this.invoice.batchNo
    let data:any = this.invoice;
    let value = {
      address:data.address,
      amount:data.amount,
      batchNo:data.batchNo.prefix,
      stockId:data.batchNo.id,
      branchForwarding:data.branchForwarding,
      cardType:data.cardType,
      cgst:data.cgst,
      cgstAmt:data.cgstAmt,
      consigneeDest:data.consigneeDest.id,
      consigneeMobNo:data.consigneeMobNo,
      consigneeadd:data.consigneeadd,
      consigneename:data.consigneename,
      consignerEmailId:data.consignerEmailId,
      consignerMobNo:data.consignerMobNo,
      custName:data.custName,
      decPer:data.decPer,
      decVal:data.decVal,
      decamt:data.decamt,
      destBranch:data.destBranch,
      docNo:data.docNo,
      docketCharges:data.docketCharges,
      edcessPer:data.edcessPer,
      fuelAmt:data.fuelAmt,
      fuelTax:data.fuelTax,
      igst:data.igst,
      igstAmt:data.igstAmt,
      invoiceNo:data.invoiceNo,
      locationCode:data.locationCode,
      locationId:data.locationId.id,
      netAmt:data.netAmt,
      noOfPieces:data.noOfPieces,
      odCharges:data.odCharges,
      orginFld:data.orginFld.id,
      partyId:data.partyId.id,
      partyLabel:data.partyLabel,
      refNo:data.refNo,
      rptNetamt:data.rptNetamt,
      sgst:data.sgst,
      sgstAmt:data.sgstAmt,
      shessPer:data.shessPer,
      status:data.status,
      tariffId:data.tariffId,
      teriffLocation:data.teriffLocation.id,
      time:data.time,
      type:data.type,
      upload:'Y',
      userId:data.userId,
      weight:data.weight,
    }
    console.log(value);
    this.transactionService.saveInvoice(value).subscribe(
      data => {
        this.loading = false;
        this.showSuccess();
      },
      err => {
        this.loading = false;
        this.map = JSON.parse(err.error).message;
      }
    );
  }


  pdf(){

  
  
  //let copt =this.reportInput;
  this.reportInput.id=this.invoice.id;
  this.reportInput.reportName="billing";
  
  let copy = JSON.parse(JSON.stringify(this.reportInput));
   
  //  copy.locationId=copy.locationId.id;
    this.loading=true;
        this.reportService.print(this.reportInput).subscribe((blob: Blob): void => {
      this.loading=false;
      
      const file = new Blob([blob], {type: 'application/pdf'});
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL, '_blank', 'width=1000, height=800');
    });
  }
  showSuccess() {
    // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
    this.messageService.add({ key: 'bc', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }

  showError(message: string) {
    // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
    this.messageService.add({ key: 'error', severity: 'error', summary: 'error', detail: message });
  }
  


}
