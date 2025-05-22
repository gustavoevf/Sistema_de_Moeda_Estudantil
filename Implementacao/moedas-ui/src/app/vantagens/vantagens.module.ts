import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VantagensComponent } from './vantagens.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VantagensRoutingModule } from './vantagens-routing.module';

@NgModule({
  declarations: [VantagensComponent],
  imports: [
    CommonModule,
    VantagensRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class VantagensModule {}
