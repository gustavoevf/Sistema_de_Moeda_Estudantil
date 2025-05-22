import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransacoesComponent } from './transacoes.component';
import { TransacoesRoutingModule } from './transacoes-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [TransacoesComponent],
  imports: [
    CommonModule,
    TransacoesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class TransacoesModule {}
