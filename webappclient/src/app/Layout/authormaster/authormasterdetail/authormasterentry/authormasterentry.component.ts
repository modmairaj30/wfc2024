import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { Authormaster } from 'src/app/Layout/domain/authormaster.model';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';
@Component({
  selector: 'app-authormasterentry',
  templateUrl: './authormasterentry.component.html',
  styleUrls: ['./authormasterentry.component.scss']
})
export class AuthormasterentryComponent implements OnInit {


  map = new Map();
  error: string;
  errorMessage:string
  
  @Input()
  authormaster = new Authormaster();
  constructor(private userService: UserService, private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {
    
  }

  save() {

    let copy = JSON.parse(JSON.stringify(this.authormaster))
  //  copy.parent = copy.parent.id;
   this.masterService.saveAuthormaster(copy).subscribe(
     data => {
       debugger;
       if (data.statusCode == "OK") {
       this.showSuccess();
       }
       else{
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

  public checkSelectOptionValue(value) {
    if (value == "undefined" || value == null) {
      return value;
    }
  }

  reset() {
    this.authormaster = new Authormaster();
  }
  showSuccess() {
      this.messageService.add({ key: 'success', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }
  showFail(value:string) {
       this.messageService.add({ key: 'error', severity: 'error', summary: 'error', detail:value });
  }
}
