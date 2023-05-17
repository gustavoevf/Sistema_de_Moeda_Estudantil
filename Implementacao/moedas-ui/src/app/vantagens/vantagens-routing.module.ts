import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserGuard } from '../shared/guards/user.guard';
import { VantagensComponent } from './vantagens.component';

const routes: Routes = [
  { path: 'vantagens', component: VantagensComponent, canActivate: [UserGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VantagensRoutingModule { }
