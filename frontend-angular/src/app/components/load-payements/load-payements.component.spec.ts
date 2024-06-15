import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadPayementsComponent } from './load-payements.component';

describe('LoadPayementsComponent', () => {
  let component: LoadPayementsComponent;
  let fixture: ComponentFixture<LoadPayementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoadPayementsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoadPayementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
