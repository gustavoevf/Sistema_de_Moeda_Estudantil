import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlunoRoutingModule } from './aluno-routing.module';
import { AlunoComponent } from './aluno.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AlunoComponent
  ],
  imports: [
    CommonModule,
    AlunoRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AlunoModule { }
