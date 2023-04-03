import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionBookComponent } from './transaction-book.component';

describe('TransactionBookComponent', () => {
  let component: TransactionBookComponent;
  let fixture: ComponentFixture<TransactionBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionBookComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransactionBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
