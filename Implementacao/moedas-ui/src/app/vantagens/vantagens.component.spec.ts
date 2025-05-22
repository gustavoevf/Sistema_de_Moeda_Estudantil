import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VantagensComponent } from './vantagens.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('VantagensComponent', () => {
  let component: VantagensComponent;
  let fixture: ComponentFixture<VantagensComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VantagensComponent],
      imports: [HttpClientTestingModule, FormsModule],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VantagensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
