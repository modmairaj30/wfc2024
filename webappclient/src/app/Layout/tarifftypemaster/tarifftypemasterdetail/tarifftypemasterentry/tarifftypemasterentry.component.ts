import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { Branchmaster } from 'src/app/Layout/domain/branchmaster';
import { MasterService } from 'src/app/layout/service';
import { TariffType } from 'src/app/layout/domain';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-tarifftypemasterentry',
  templateUrl: './tarifftypemasterentry.component.html',
  styleUrls: ['./tarifftypemasterentry.component.scss']
})
export class TarifftypemasterentryComponent implements OnInit {

  @Input()
  tariffType = new TariffType();
  constructor(private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {

  }
  save() {
    this.masterService.saveTariffType(this.tariffType).subscribe(
      data => {
        this.showSuccess();
        // alert(data.data);
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
