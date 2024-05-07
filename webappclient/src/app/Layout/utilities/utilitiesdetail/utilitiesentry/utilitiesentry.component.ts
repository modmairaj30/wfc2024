import { Component, OnInit, ViewChild, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { ServerSidePagination } from 'src/app/Layout/domain/serversidepagination';
import { MessageService } from 'primeng/api';
import { LocationService, MasterService, TransactionService } from 'src/app/layout/service';
import { SearchCriteria } from 'src/app/Layout/domain/searchCriteria.model';
import { GlobalConstants } from 'src/app/global-constants';
import { DatexPipe } from 'src/app/shared/pipes/date.pipe';
import { FilterPipe } from 'src/app/shared/pipes/filter.pipe';
import { LookUpFormBean, ReportInput, StockVerification } from 'src/app/layout/domain';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ReportService } from 'src/app/Layout/service/reportservice';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { NormalReportService } from 'src/app/Layout/service/normalreportservice';
import { JasperDynamicReportService } from 'src/app/Layout/service/jasperdynamicreportservice';


@Component({
  selector: 'app-Utilitiesentry',
  templateUrl: './Utilitiesentry.component.html',
  styleUrls: ['./Utilitiesentry.component.sass'],
  providers: [MessageService]
})
export class UtilitiesentryComponent implements OnInit {



  map = new Map();
  error: string;
  errorMessage: string;
  loading: boolean = false;
  isFromDate: boolean = true;
  jasperName: string;
  isJasper: boolean = true;
  isToDate: boolean = true;
  isLocation: boolean = true;
  isSupplier: boolean = true;
  isAuthor: boolean = true;
  filteredEmpList1: any[];
  filteredEmpList2: any[];
  filteredEmpList3: any[];
  filteredEmpList4: any[];
  filteredEmpList5: any[];
  selectedFile: File;
  locationList1: any[] = [];
  Utilities2: any[] = [];
  Utilities3: any[] = [];
  Utilities4: any[] = [];
  Utilities5: any[] = [];
  lookUpFormBean1 = new LookUpFormBean(); // location quary
  lookUpFormBean2 = new LookUpFormBean(); // report quary
  lookUpFormBean3 = new LookUpFormBean();
  lookUpFormBean4 = new LookUpFormBean(); // location quary
  lookUpFormBean5 = new LookUpFormBean(); // report quary
  listLookUpFormBean: LookUpFormBean[] = new Array();
  public reportInput = new ReportInput();

  selectedRowData: any[];

  content: any[] = [];


  selectedColumns: any[];


  Utilities: any;

  head = [];
  body = [];
  foot = [];
  dhead = [];
  dbody = [];
  dfoot = [];
  count: number = 0;
  title: string = "Stock In C Note";
  public viewResponseData = {
    'reportColumns': [],
    'objects': [],
    'title': '',
    'header': []
  };
  // tslint:disable-next-line: max-line-length
  constructor(private userService: UserService, private reportService: NormalReportService, private messageService: MessageService, private masterService: MasterService, private jasperService: JasperDynamicReportService) {

    this.lookUpFormBean1.query = "select id,location_name from tbl_location";
    this.lookUpFormBean2.query = "select id, table_name, field_names from import_data";
    this.lookUpFormBean3.query = "select id, table_name, field_names from import_data";
    

    this.lookUpFormBean1.listName = 'LIST1';
    this.lookUpFormBean2.listName = 'LIST2';
    this.lookUpFormBean3.listName = 'LIST3';
   
  
    this.listLookUpFormBean.push(this.lookUpFormBean1);
    this.listLookUpFormBean.push(this.lookUpFormBean2);
    this.listLookUpFormBean.push(this.lookUpFormBean3);
    
   
    this.userService.getMultipleDropdwonValues(this.listLookUpFormBean).subscribe(
      data => {
        this.locationList1 = JSON.parse(JSON.stringify(data.data.LIST1));
        this.Utilities2 = JSON.parse(JSON.stringify(data.data.LIST2));
        this.Utilities3 = JSON.parse(JSON.stringify(data.data.LIST3));
        
       
      },
      err => {
        this.map = JSON.parse(err.error).message;
      }

    );

  }
  ngOnInit(): void {
    this.selectedRowData = [];

  }

  onTableHeaderCheckboxToggle(event: any) {
  }

  filterEmpList1(event) {
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.locationList1.length; i++) {
      let item = this.locationList1[i];
      if (item.location_name.toLowerCase().includes(query.toLowerCase())) {
        filtered.push(item);
      }
    }

    this.filteredEmpList1 = filtered;
  }
  filterEmpList2(event) {
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.Utilities2.length; i++) {
      let item = this.Utilities2[i];
      //  if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
      if (item.table_name.toLowerCase().includes(query.toLowerCase())) {
        filtered.push(item);
      }
    }

    this.filteredEmpList2 = filtered;
  }
  filterEmpList3(event) { // purchase register
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.Utilities3.length; i++) {
      let item = this.Utilities3[i];
      //  if (item.location_name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
      if (item.table_name.toLowerCase().includes(query.toLowerCase())) {
        filtered.push(item);
      }
    }

    this.filteredEmpList3 = filtered;
  }
  

  



  normalReport() {
    let copy = JSON.parse(JSON.stringify(this.reportInput));
    //  copy.locationId=copy.locationId.id;
    this.loading = false;
    if (this.reportInput.locationId != null || this.reportInput.locationId != undefined) {
      copy.locationId = this.reportInput.locationId.id;
    }
    copy.reportId = this.reportInput.reportName.report_id;
    copy.reportName = this.reportInput.reportName.report_name;
    copy.reportType = this.reportInput.reportType.report_type;
    if (this.reportInput.fromDate != null || this.reportInput.fromDate != undefined) {
      copy.fromDate = this.reportInput.fromDate.getTime();
    }
    if (this.reportInput.toDate != null || this.reportInput.toDate != undefined) {
      copy.toDate = this.reportInput.toDate.getTime();
    }
    this.reportService.getReport(copy).subscribe(
      data => {
        this.viewResponseData = JSON.parse(JSON.stringify(data.data));
      },
      err => {
        this.viewResponseData = JSON.parse(err.error).message;
      }
    );
  }
  jasperReport() {
    this.pdf();
  }
  selectedAutoCompletedValue(event) {
    debugger;
    if (event.from_date == 'Y') {
      this.isFromDate = false;
    }
    else {
      this.isFromDate = true;
      this.reportInput.fromDate = null;
    }
    if (event.to_date == 'Y') {
      this.isToDate = false;
    } else {
      this.isToDate = true;
      this.reportInput.toDate = null;
    }
    if (event.location == 'Y') {
      this.isLocation = false;
    } else {
      this.isLocation = true;
      this.reportInput.locationId = null;
    }
    if (event.supplier == 'Y') {
      this.isSupplier = false;
      this.reportInput.reportName = this.reportInput.reportName.report_name;
    } else { 
      this.isSupplier = true;
      this.reportInput.partyId = null;  
    }
    if (event.author == 'Y') {
      this.isAuthor = false;
      this.reportInput.reportName = this.reportInput.reportName.report_name;
    } else {
      this.isAuthor = true;
      this.reportInput.authorId = null;
    }
    if (event.jasper == 'Y') {
      debugger;
      this.isJasper = true;
      this.jasperName = event.jasper_name;
      this.reportInput.jasperName = event.jasper_name;
      this.reportInput.reportName = this.reportInput.reportName.report_name;
    } else { this.isJasper = false; }

  }

  onRowSelect(event) {
    this.count = 1;
    var datePipe = new DatexPipe();
    var filterPipe = new FilterPipe();
    let currentDt = datePipe.transform(new Date(), 'DD/MM/yyyy');
    // let docDt = datePipe.transform(this.reportInput.toDate, 'DD/MM/yyyy');
    // let locationName = filterPipe.transform(this.locationList1, this.reportInput.locationId.id, "locationName");
    this.dfoot = [],
      this.body = [], this.head = [];
    this.title = this.reportInput.reportName.report_name + " - " + currentDt;
    let h = [];

    for (let j = 0; j <= this.viewResponseData.header.length - 1; j++) {
      h[j] = this.viewResponseData.header[j].field;
    }
    this.dhead = [h];

    for (let i = 0; i <= this.viewResponseData.objects.length - 1; i++) {
      let d = [];
      for (let j = 0; j <= this.viewResponseData.header.length - 1; j++) {

        d[j] = this.viewResponseData.objects[i][this.viewResponseData.header[j].field];
      }
      this.dbody.push(d);
    }

  }

  onRowUnselect(event) {
    event.checked;
    this.count = 0;

  }
  ////jasper report execution code start
  pdf() {

    //let copt =this.reportInput;


    let copy = JSON.parse(JSON.stringify(this.reportInput));

    //  copy.locationId=copy.locationId.id;
    this.loading = true;
    debugger
    if(!this.isLocation){
    this.reportInput.locationId = this.reportInput.locationId.id;
    }
    
  }

  excel() {

    //let copt =this.reportInput;


    let copy = JSON.parse(JSON.stringify(this.reportInput));

    //  copy.locationId=copy.locationId.id;
    this.loading = true;
    this.reportInput.locationId = this.reportInput.locationId.id;
    this.reportInput.jasperName = this.jasperName;
    this.jasperService.reportExportToExcel(this.reportInput).subscribe((blob: Blob): void => {
      this.loading = false;
      debugger;
      const file = new Blob([blob], { type: 'application/vnd.ms-excel' });
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL, '_blank', 'width=1000, height=800');
    });
  }
  ///jasper report execution code end
  //Gets called when the user selects an image
  public onFileChanged(event) {
    this.selectedFile = event.files[0];
    var reader = new FileReader();

    reader.readAsDataURL(event.files[0]); // read file as data url

    reader.onload = (event) => { // called once readAsDataURL is completed
       event.target.result;
    }
    this.messageService.add({ key: 'bc', severity: 'success', summary: 'success', detail: 'Profile Pic Uploaded Successfully.' });
    //this.usermaster.picByte = event.target.files[0];
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
    }
uploadedFile(event) {  
  this.selectedFile = event.target.files[0];  
  
}  
  /////
  onChange(event) { 
    this.selectedFile = event.target.files[0]; 
} 

// OnClick of button Upload 
    onUpload() { 
        this.loading = !this.loading; 
        console.log(this.selectedFile); 
        this.masterService.upload(this.selectedFile).subscribe( 
            (event: any) => { 
                if (typeof (event) === 'object') { 
                 this.loading = false; // Flag variable  
                } 
            } 
        ); 
    }

  save() {
    debugger;
    let formData: FormData = new FormData();
    if (this.selectedFile != null) {
      formData.append('file', this.selectedFile, this.selectedFile.name);
    }
      
     
      formData.append('table',"abc");
     
       
    this.masterService.saveExcelData(formData).subscribe(
      data => {
        if (data.body.message == 'ok') {
          this.showSuccess();
        }
        else {
          this.showFail(data.body.message);

        }
        // this.usermaster = new UserMaster();
      },
      err => {
        this.map = JSON.parse(err.error).message;
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


