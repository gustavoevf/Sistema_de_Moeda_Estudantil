import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TransacoesComponent } from './transacoes.component';

const routes: Routes = [{ path: 'transacoes', component: TransacoesComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TransacoesRoutingModule {}
