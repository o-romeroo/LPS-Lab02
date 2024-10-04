import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutomoveisComponent } from './automoveis.component';

const routes: Routes = [
  { path: "", component: AutomoveisComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AutomoveisRoutingModule { }