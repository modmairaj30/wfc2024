import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UtilitiesdetailComponent } from './utilitiesdetail.component';

describe('UtilitiesdetailComponent', () => {
  let component: UtilitiesdetailComponent;
  let fixture: ComponentFixture<UtilitiesdetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UtilitiesdetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UtilitiesdetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
