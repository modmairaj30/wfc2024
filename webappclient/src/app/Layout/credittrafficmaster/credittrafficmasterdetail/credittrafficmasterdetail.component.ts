import { Component, OnInit } from '@angular/core';
import { Credittarriffmaster } from '../../domain';
import { ListboxModule } from 'primeng/listbox';


@Component({
  selector: 'app-credittrafficmasterdetail',
  templateUrl: './credittrafficmasterdetail.component.html',
  styleUrls: ['./credittrafficmasterdetail.component.css']
})
export class CredittrafficmasterdetailComponent implements OnInit {
  active: 1;
  editmod: boolean = false;
  credittarriffmaster = new Credittarriffmaster();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }

  editCredittarriffmaster(credittarriffmaster: any) {

    this.credittarriffmaster = credittarriffmaster;
    this.editmod = true;
    this.active = 1;
  }
  clear() {
    this.credittarriffmaster = new Credittarriffmaster();
  }
  tabChange() {
    if (this.active == 1) {
      this.editmod = false;
      this.clear();
    }
  }

}
