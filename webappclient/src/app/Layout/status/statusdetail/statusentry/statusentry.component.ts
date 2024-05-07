import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MasterService } from 'src/app/layout/service';
import { Status } from 'src/app/layout/domain';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-statusentry',
  templateUrl: './statusentry.component.html',
  styleUrls: ['./statusentry.component.scss']
})
export class StatusentryComponent implements OnInit {

  @Input()
  status1 = new Status();
  constructor(private masterService: MasterService, private messageService: MessageService) { }

  ngOnInit(): void {

  }
  save() {
    this.masterService.saveStatus(this.status1).subscribe(
      data => {
        this.showSuccess();
        //  alert(data.data);
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
