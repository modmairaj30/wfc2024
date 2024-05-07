import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from "rxjs/operators";
import { Partymaster } from 'src/app/Layout/domain/partymaster';
import { environment } from 'src/environments/environment';
import {  Employeemaster, ServerSidePagination, Locationmaster,LookUpFormBean, Branchmaster, Credittarriffmaster, Mode, CardType, TariffType, Status, Destinationmaster, Synchronize } from '../domain';
import { Departmentmaster } from '../domain/departmentmaster.model';
import { ContraLocationmaster } from '../domain/contralocationmsater.model';
import { Authormaster } from '../domain/authormaster.model';
import { Categorymaster } from '../domain/categoryMaster.model';
import { ProductGroupMaster } from '../domain/ProductGroupMaster.model';
import { Subjectmaster } from '../domain/subjectsMaster.model';
import { PaymentModemaster } from '../domain/paymentmodemaster.model';
import { Designationmaster } from '../domain/designationmaster.model';
import { TitleMaster } from '../domain/titlemaster.model';
import { Membershipmaster } from '../domain/membershipmaster.model';
import { SearchCriteria } from '../domain/searchCriteria.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const httpOptions1 = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }).set('Accept', 'application/json' )
 // headers.append({ 'Accept': 'application/json' });
  //headers=headers.append('Access-Control-Allow-Origin', '*')
//headers=headers.append('content-type','application/x-www-form-urlencoded')
 
  
};



const httpOptions2 = {
  headers: new HttpHeaders({ 'Content-Type': 'file' }).set('Accept', 'application/json' )
 // headers.append({ 'Accept': 'application/json' });
  //headers=headers.append('Access-Control-Allow-Origin', '*')
//headers=headers.append('content-type','application/x-www-form-urlencoded')
  
};

@Injectable({
  providedIn: 'root'
})

export class MasterService {
  findDestinationMaster(searchCriteria: SearchCriteria) {
    throw new Error('Method not implemented.');
  }
    public actionUrl: string;
    public headers: Headers;
  getAllDestinationMaster: any;
    
  constructor(private http: HttpClient) { 
    this.actionUrl = environment.apiUrl+'/masters';
   }

  getPublicContent(): Observable<any> {
    
    return this.http.get(this.actionUrl + '/getAllPartys', { responseType: 'json' });
  }

 
  savePartyMaster(partymaster:Array<Partymaster>): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/savePartyMaster',partymaster,httpOptions);
  }

    getAllEmployees(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllEmployees', { responseType: 'json' });
  }
  saveEmployeeMaster(employeemaster:Employeemaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveEmployeeMaster',employeemaster,httpOptions);
  } 
  
  getAllTitleMaster(serverSidePagination:ServerSidePagination): Observable<any> {
    
    return this.http.get(this.actionUrl + '/getAllTitleMaster?page='+serverSidePagination.page+'&size='+serverSidePagination.size, { responseType: 'json' });
  }

 getBranches(): Observable<any> {

   // return this.http.get(this.actionUrl + '/getAllBranches', { responseType: 'json' });

    return this.http.get(this.actionUrl + '/getAllBranches', { responseType: 'json' }).pipe(
      catchError(err=>{
        console.error('error cought in service', err)
         return throwError(err);
      })
    );
  }
  saveTitleMaster(titlemaster:TitleMaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveTitleMaster',titlemaster,httpOptions);
  }
  saveBranchMaster(branchmaster: Branchmaster): Observable<any> {
    return this.http.post(this.actionUrl + '/saveBranchMaster',branchmaster,httpOptions);
   // return this.http.post(this.actionUrl + '/saveBranchMaster', branchmaster, httpOptions);
  }
  
  
  
  
  findTitleMaster(searchCriteria:SearchCriteria): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/findTitleMaster',searchCriteria,httpOptions);
  }

  findCreditTrafficMaster(searchCriteria:SearchCriteria): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/findCreditTrafficMaster',searchCriteria,httpOptions);
  }

  findPartyMaster(searchCriteria:SearchCriteria): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/findPartyMaster',searchCriteria,httpOptions);
  }
  findAuthormaster(searchCriteria:SearchCriteria): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/findAuthorMaster',searchCriteria,httpOptions);
  }

  getAllPartys(serverSidePagination:ServerSidePagination): Observable<any> {
    
    return this.http.get(this.actionUrl + '/getAllPartys?page='+serverSidePagination.page+'&size='+serverSidePagination.size, { responseType: 'json' });
  }

  getAllLocations(): Observable<any> {
    debugger;
    return this.http.get(this.actionUrl+ '/getAllLocations', { responseType: 'json' });
  }

 
  saveLocationMaster(locationmaster:Locationmaster): Observable<any> {
    //http://localhost:8080/webapp/masters/saveLocationMaster
    return this.http.post(this.actionUrl+ '/saveLocationMaster',locationmaster,httpOptions);
  } 

  
  getAllDepartment(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllDepartment', { responseType: 'json' });
  }

  saveDepartmentMaster(departmentmaster:Departmentmaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveDepartmentMaster',departmentmaster,httpOptions);
  } 

  getAllContralocation(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllContralocation', { responseType: 'json' });
  }

  saveContraLocationMaster(contraLocationmaster:ContraLocationmaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveContraLocationMaster',contraLocationmaster,httpOptions);
  }

  getAllAuthor(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllAuthor', { responseType: 'json' });
  }
   

  saveAuthormaster(authormaster:Authormaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveAuthorMaster',authormaster,httpOptions);
  } 

  getAllCategory(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllCategory', { responseType: 'json' });
  }

  saveCategorymaster(categorymaster:Categorymaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveCategoryMaster',categorymaster,httpOptions);
  } 

  getAllproductGroup(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllProductGroup', { responseType: 'json' });
  }

  saveproductGroupMaster(productGroupMaster:ProductGroupMaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveProductGroupMaster',productGroupMaster,httpOptions);
  } 
  
  getAllSubject(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllSubject', { responseType: 'json' });
  }
  saveSubjectmaster(subjectmaster:Subjectmaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveSubjectMaster',subjectmaster,httpOptions);
  } 

  getAllpaymentmsater(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllPaymentmode', { responseType: 'json' });
  }
  
  savePaymnetModemaster(paymentModemaster:PaymentModemaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/savePaymnetModeMaster',paymentModemaster,httpOptions);
  } 

  getAlldesignationmaster(): Observable<any> {
    
    return this.http.get(this.actionUrl+ '/getAllDesignation', { responseType: 'json' });
  }

  saveDesignationmaster(designationmaster:Designationmaster): Observable<any> {
    
    return this.http.post(this.actionUrl+ '/saveDesignationMaster',designationmaster,httpOptions);
  } 
  getDestinations(): Observable<any> {

    return this.http.get(this.actionUrl + '/getAllDestinations', { responseType: 'json' });
  }

  saveDestinationMaster(destinationmaster: Destinationmaster): Observable<any> {

    return this.http.post(this.actionUrl + '/saveDestinationMaster', destinationmaster, httpOptions);
  }
 
  saveMembershipMaster(membershipmaster:Membershipmaster): Observable<any> {
      
    return this.http.post(this.actionUrl+ '/saveMembershipMaster',membershipmaster,httpOptions);
  }
  
  getAllMembershipMaster(serverSidePagination:ServerSidePagination): Observable<any> {
    
    return this.http.get(this.actionUrl + '/getAllMembers?page='+serverSidePagination.page+'&size='+serverSidePagination.size, { responseType: 'json' });
  }
  
   saveCreditteriffMaster(credittarriffmaster: Credittarriffmaster): Observable<any> {

    return this.http.post(this.actionUrl + '/saveCreditTeriffmaster', credittarriffmaster, httpOptions);
  }


  conditionSlabs(credittarriffmaster: Credittarriffmaster): Observable<any> {

    return this.http.post(this.actionUrl + '/conditionSlabs', credittarriffmaster, httpOptions);
  }

  getCreditteriffMaster(serverSidePagination: ServerSidePagination): Observable<any> {

    return this.http.get(this.actionUrl + '/getAllCreditTeriffmaster?page=' + serverSidePagination.page + '&size=' + serverSidePagination.size, { responseType: 'json' });
  }
  
  getAllCardType(): Observable<any> {

    return this.http.get(this.actionUrl + '/getAllCardType', { responseType: 'json' });
  }

  saveCardType(cardType: CardType): Observable<any> {

    return this.http.post(this.actionUrl + '/saveCardType', cardType, httpOptions);
  }
  
  getAllTariffType(): Observable<any> {

    return this.http.get(this.actionUrl + '/getAllTariffType', { responseType: 'json' });
  }

  saveTariffType(tariffType: TariffType): Observable<any> {

    return this.http.post(this.actionUrl + '/saveTariffType', tariffType, httpOptions);
  }

  getAllStatus(): Observable<any> {

    return this.http.get(this.actionUrl + '/getAllStatus', { responseType: 'json' });
  }

  saveStatus(status: Status): Observable<any> {

    return this.http.post(this.actionUrl + '/saveStatus', status, httpOptions);
  }

  getAllMode(): Observable<any> {

    return this.http.get(this.actionUrl + '/getAllMode', { responseType: 'json' });
  }

  saveMode(mode: Mode): Observable<any> {
    return this.http.post(this.actionUrl + '/saveMode', mode, httpOptions);
  }

  getDropdownTblExcelUpload(): Observable<any> {
    return this.http.get(this.actionUrl + '/getAllTblExcelUpload' , httpOptions);
  }

  saveExcelData(formData): Observable<any> {
    return this.http.post(this.actionUrl + '/uploadExcel', formData);
  }
   
  upload(file):Observable<any> { 
  
    // Create form data 
    const formData = new FormData();  
      
    // Store form name as "file" with file data 
    formData.append("file", file, file.name); 
    formData.append("table","tutorials")  
    // Make http post request over api 
    // with formData as req 
    return this.http.post(this.actionUrl + '/uploadExcel', formData) 
} 

uploadDataToServer(s: Synchronize): Observable<any> {

  return this.http.post(this.actionUrl + '/uploadtables', s, httpOptions);
}

downloadDataFromServer(s: Synchronize): Observable<any> {

  return this.http.post(this.actionUrl + '/downloadtables', s, httpOptions);
}

  getLookupValues(lookUpFormBean:LookUpFormBean): Observable<any> {
    return this.http.post(this.actionUrl + '/getLookUpValues',lookUpFormBean,httpOptions).pipe(
      catchError(err=>{
        console.error('error cought in service', err)
         return throwError(err);
      })
    );
  }

  getMultipleDropdwonValues(listLookUpFormBean: Array<LookUpFormBean>): Observable<any> {
    return this.http.post(this.actionUrl + '/getMultipleDropdwonValues',listLookUpFormBean,httpOptions).pipe(
      catchError(err=>{
        console.error('error cought in service', err)
         return throwError(err);
      })
    );
}

}
