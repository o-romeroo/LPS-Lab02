import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AutomoveisComponent } from './automoveis.component';
import { AutomoveisRoutingModule } from './automoveis-routing.module';
import { SharedModule } from "../shared/shared.module";
import { MaterialModule } from 'src/app/material-module';
import { AutomovelService } from 'src/app/service/automovel/automovel.service';
import { TablePaginationAutomovelComponent } from './table-pagination-automovel/table-pagination-automovel.component';
import { CreateAutomovelDialogComponent } from './create-automovel-dialog/create-automovel-dialog.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { EditAutomovelDialogComponent } from './edit-automovel-dialog/edit-automovel-dialog.component';
import { DeleteAutomovelDialogComponent } from './delete-automovel-dialog/delete-automovel-dialog.component';



@NgModule({
  declarations: [
    AutomoveisComponent,
    TablePaginationAutomovelComponent,
    CreateAutomovelDialogComponent,
    EditAutomovelDialogComponent,
    DeleteAutomovelDialogComponent
  ],
  imports: [
    CommonModule,
    AutomoveisRoutingModule,
    FlexLayoutModule,
    MaterialModule,
    SharedModule
]
})
export class AutomoveisModule { }
