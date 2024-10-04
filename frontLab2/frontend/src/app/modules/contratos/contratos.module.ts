import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from "../shared/shared.module";
import { MaterialModule } from 'src/app/material-module';
import { TablePaginationContratosComponent } from './table-pagination-contratos/table-pagination-contratos.component';
import { ContratosComponent } from './contratos.component';
import { ContratosRoutingModule } from './contratos-routing.module';
import { DetalhesDialogComponent } from './table-pagination-contratos/detalhes-dialog/detalhes-dialog.component';




@NgModule({
  declarations: [
    ContratosComponent,
    TablePaginationContratosComponent,
    DetalhesDialogComponent
  ],
  imports: [
    CommonModule,
    ContratosRoutingModule,
    MaterialModule,
    SharedModule
]
})
export class ContratosModule { }
