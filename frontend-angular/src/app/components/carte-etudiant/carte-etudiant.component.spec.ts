import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarteEtudiantComponent } from './carte-etudiant.component';

describe('CarteEtudiantComponent', () => {
  let component: CarteEtudiantComponent;
  let fixture: ComponentFixture<CarteEtudiantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarteEtudiantComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarteEtudiantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
