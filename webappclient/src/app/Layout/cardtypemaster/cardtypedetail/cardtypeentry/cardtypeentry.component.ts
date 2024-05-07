import { Component, OnInit, Input } from '@angular/core';
import { UserService } from 'src/app/DemoPages/UserPages/_services/user.service';
import { CardType } from 'src/app/layout/domain';
import { LookUpFormBean } from 'src/app/Layout/domain/lookupform';
import { Partymaster } from 'src/app/Layout/domain/partymaster.model';
import { MasterService } from 'src/app/layout/service';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-cardtypeentry',
  templateUrl: './cardtypeentry.component.html',
  styleUrls: ['./cardtypeentry.component.scss']
})
export class CardtypeentryComponent implements OnInit {

  @Input()
  cardtype = new CardType();
  constructor(private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {

  }
  save() {
    this.masterService.saveCardType(this.cardtype).subscribe(
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
