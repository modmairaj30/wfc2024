import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { Branchmaster } from 'src/app/Layout/domain/branchmaster';
import { MasterService } from 'src/app/layout/service';
import { Mode } from 'src/app/layout/domain';
import { MessageService } from 'primeng/api';
@Component({
  selector: 'app-modeentry',
  templateUrl: './modeentry.component.html',
  styleUrls: ['./modeentry.component.scss']
})
export class ModeentryComponent implements OnInit {

  @Input()
  mode = new Mode();
  constructor(private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {

  }
  save() {
    this.masterService.saveMode(this.mode).subscribe(
      data => {
        this.showSuccess();
        //alert(data.data);
      },
      err => {
        // this.map = JSON.parse(err.error).message;
      }
    );
  }
  showSuccess() {
    // this.messageService.add({severity:'success', summary: 'Success', detail: 'Message Content'});
    this.messageService.add({ key: 'bc', severity: 'success', summary: 'success', detail: 'Data saved Successfully.' });
  }

}
