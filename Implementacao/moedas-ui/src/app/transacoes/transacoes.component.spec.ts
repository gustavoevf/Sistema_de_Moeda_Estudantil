import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransacoesComponent } from './transacoes.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';

describe('TransacoesComponent', () => {
  let component: TransacoesComponent;
  let fixture: ComponentFixture<TransacoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TransacoesComponent],
      imports: [HttpClientTestingModule, FormsModule],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
