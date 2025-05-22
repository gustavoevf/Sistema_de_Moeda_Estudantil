import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VantagensComponent } from './vantagens.component';

const routes: Routes = [{ path: 'vantagens', component: VantagensComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VantagensRoutingModule {}
