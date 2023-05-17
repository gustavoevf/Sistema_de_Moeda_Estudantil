import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VantagensComponent } from './vantagens.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    VantagensComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class VantagensModule { }
