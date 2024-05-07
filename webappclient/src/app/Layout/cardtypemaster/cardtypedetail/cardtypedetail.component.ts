import { Component, OnInit } from '@angular/core';
import { CardType } from '../../domain';


@Component({
  selector: 'app-cardtypedetail',
  templateUrl: './cardtypedetail.component.html',
  styleUrls: ['./cardtypedetail.component.css']
})
export class CardtypedetailComponent implements OnInit {
  active: 1;
  cardType = new CardType();
  constructor() { }

  ngOnInit(): void {
    this.active = 1;
  }

  editCardType(cardType: any) {

    this.cardType = cardType;
    this.active = 1;
  }
  clear() {
    this.cardType = new CardType();
  }
  tabChange() {
    if (this.active == 1) {
      this.clear();
    }
  }

}
