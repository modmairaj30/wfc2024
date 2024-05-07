import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { QueryList, ViewChildren } from '@angular/core';
import { Observable } from 'rxjs';
import jsPDF from 'jspdf';
import { ServerSidePagination } from 'src/app/Layout/domain/serversidepagination';
import { GlobalConstants } from 'src/app/global-constants';
import { SearchCriteria } from 'src/app/layout/domain/searchCriteria.model';
//import * as jsPDF from 'jspdf';
import 'jspdf-autotable';

import html2canvas from 'html2canvas';



import {
  UserService,
} from 'src/app/DemoPages/UserPages/_services/user.service';
import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-modehistory',
  templateUrl: './modehistory.component.html',
  styleUrls: ['./mode.scss'],
  providers: [MessageService]
})
export class ModeHistoryComponent implements OnInit {
  selectedRowData: any[];
  user: any = [];
  loading:boolean=false;
  content: any[] = [];
  cols: any[];
  _selectedColumns: any[];
  selectedColumn: string;
  searchs: any[];
  public columnNames: string[] =["Branch","Code"]

  @Output()
  editBranchentry = new EventEmitter();
  public serverSidePagination = new ServerSidePagination();
  public searchCriteria = new SearchCriteria();

  constructor(private masterService: MasterService, private messageService: MessageService) {

    this.cols = [
      {  checked:1,width: 'width:10%', field: 'id', header: 'Id' },

      {  checked:1,width: 'width:15%', field: 'authorName', header: 'Author' },
      {  checked:1,width: 'width:15%', field: 'address', header: 'Address' },
      {  checked:1,width: 'width:10%', field: 'email', header: 'Email' },
      {  checked:1,width: 'width:10%', field: 'phoneNumber', header: 'Phone Number' },
      {  checked:0,width: 'width:10%', field: 'createdBy', header: 'Created By' },
      {  checked:0,width: 'width:10%', field: 'createdDate', header: 'Created Date' },
      {  checked:0,width: 'width:10%', field: 'updatedBy', header: 'Updated By' },
      {  checked:0,width: 'width:10%', field: 'updatedDate', header: 'Updated Date' }
    ];

    this._selectedColumns = this.cols;
    this.searchs=GlobalConstants.searchs;
    this.searchCriteria.key=this.cols[0].field;
    this.searchCriteria.value="";
    this.searchCriteria.operation=this.searchs[0].field;
  }

  ngAfterViewInit(): void {

  }
  ngOnInit(): void {
    this.selectedRowData = [];
    this.masterService.getAllAuthor().subscribe(
      data => {
        
        this.content = JSON.parse(JSON.stringify(data.data));
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

   
    //  this.loadEmployees()
  }

  editProduct(product: any) {
    this.editBranchentry.emit(product);
  }
  onTableHeaderCheckboxToggle(event: any) {
  }
  print() {
    if(this.selectedRowData.length===0){
      alert("please select a row to print");
      return 0;
    }
   // let printContents, popupWin;
    //printContents = document.getElementById('dt').innerHTML;
    let dataurl = document.getElementById("qrcode");
    
        let imgdata = dataurl.getElementsByTagName("img")[0].getAttribute("src");
    let popupWin = window.open('', 'PRINT', 'top=0,left=0,height=100%,width=auto');
    popupWin.document.write('<html><head><style></style></head><body><img src="../../assets/images/logo.png" class="img1" alt="cinque terre">');
    popupWin.document.write('<img src="'+imgdata+'" >');
    popupWin.document.write('<h3 align="center">Branch Details</h3><table border="0">');
    popupWin.document.write('<tr><td>Branch</td><td>Address</td><td> Location Name</td><td>Region</td></tr>');
    for(let i=0;i<=this.selectedRowData.length;i++){
    popupWin.document.write('<tr><td>'+this.selectedRowData[i].branch+'</td>');
    popupWin.document.write('<td>'+this.selectedRowData[i].address+'</td>');
    popupWin.document.write('<td>'+this.selectedRowData[i].locationName+'</td>');
    popupWin.document.write('<td>'+this.selectedRowData[i].region+'</td></tr>');
    }
   
    popupWin.document.write('</table></body></html>');
    popupWin.document.close();
    popupWin.focus();
    popupWin.print();
   popupWin.close();


    

  }

  exportExcel() {
    if(this.selectedRowData.length===0){
      alert("please select a row to print");
      return 0;
    }
    import("xlsx").then(xlsx => {
      const worksheet = xlsx.utils.json_to_sheet(this.selectedRowData);
      const workbook = { Sheets: { data: worksheet }, SheetNames: ["data"] };
      const excelBuffer: any = xlsx.write(workbook, {
        bookType: "xlsx",
        type: "array"
      });
      this.saveAsExcelFile(excelBuffer, "branch master");
    });
  }

  saveAsExcelFile(buffer: any, fileName: string): void {
    import("file-saver").then(FileSaver => {
      let EXCEL_TYPE ="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8";
      let EXCEL_EXTENSION = ".xlsx";
      const data: Blob = new Blob([buffer], {
        type: EXCEL_TYPE
      });
      FileSaver.saveAs(
        data,
        fileName + "_export_" + new Date().getTime() + EXCEL_EXTENSION
      );
    });
  }
  public openPDF():void {
    if(this.selectedRowData.length===0){
      alert("please select a row to print");
      return ;
    }
   let head = [['Branch', 'Address', 'Location Name', 'Region']];
   let tableData = [];
   for(let i=0;i<=this.selectedRowData.length-1;i++){
    // 
     /*var temp = [];
     temp[0] = this.content[i].branch == null ? "" : this.content[i].branch;
           temp[1] = this.content[i].address == null ? "" :this.content[i].address;
           temp[2] = this.content[i].locationName == null ? "" : this.content[i].locationName;
           temp[3] = this.content[i].region == null ? "" :this.content[i].region;
           temp[4] = this.content[i].convLife == null ? "" : this.content[i].convLife;
           temp[5] = this.content[i].tapHoleLife == null ? "" :this.content[i].tapHoleLife;*/
  tableData.push([this.selectedRowData[i].branch,this.selectedRowData[i].address,this.selectedRowData[i].locationName,this.selectedRowData[i].region])
}
 
    let doc = new jsPDF('p', 'mm', 'a4');


    doc.setFontSize(18);
    doc.text('Branch Master', 11, 8);
    doc.setFontSize(11);
    doc.setTextColor(100);


    (doc as any).autoTable({
      head: head,
      body: tableData,
      theme: 'plain',
      didDrawCell: data => {
        console.log(data.column.index)
      }
    })

    // below line for Open PDF document in new tab
    doc.output('dataurlnewwindow')

    // below line for Download PDF document  
    doc.save('myteamdetail.pdf');
  }

  find(): void {
    this.loading = true;
    debugger;
    this.masterService.findAuthormaster(this.searchCriteria).subscribe(
      data => {
        this.loading = false;
        this.content = JSON.parse(JSON.stringify(data.data));
      },
      err => {
        this.loading = false;
        this.content = JSON.parse(err.error).message;
      }
    );

  }

}


