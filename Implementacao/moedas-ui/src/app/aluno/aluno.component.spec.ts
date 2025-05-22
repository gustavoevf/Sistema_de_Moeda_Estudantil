import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunoComponent } from './aluno.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';

describe('AlunoComponent', () => {
  let component: AlunoComponent;
  let fixture: ComponentFixture<AlunoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AlunoComponent],
      imports: [HttpClientTestingModule, FormsModule],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlunoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
