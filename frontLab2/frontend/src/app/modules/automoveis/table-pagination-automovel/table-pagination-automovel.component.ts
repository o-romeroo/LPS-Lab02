import { Component, Input, OnInit } from '@angular/core';
import { Automovel } from 'src/app/models/automoveis/model';
import { AutomovelService } from 'src/app/service/automovel/automovel.service';
import { EditAutomovelDialogComponent } from '../edit-automovel-dialog/edit-automovel-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { DeleteAutomovelDialogComponent } from '../delete-automovel-dialog/delete-automovel-dialog.component';

@Component({
  selector: 'app-table-pagination-automovel',
  templateUrl: './table-pagination-automovel.component.html',
  styleUrls: ['./table-pagination-automovel.component.scss']
})
export class TablePaginationAutomovelComponent implements OnInit {


  @Input() dataSource!: Automovel[];
  displayedColumns: string[] = ['id', 'marca', 'modelo', 'ano', 'placa', 'valor', 'detalhes'];

  constructor(private dialog:MatDialog) {}

  ngOnInit(): void {


  }

  openDeleteDialog(idAutomovel: any){
    this.dialog.open(DeleteAutomovelDialogComponent, {
      data: idAutomovel,
      width: 'max-content',
      height: 'max-content',
      panelClass: '',
      enterAnimationDuration: '350ms',
      exitAnimationDuration: '350ms'
    });
  }

  openEditDialog(data:any){
    this.dialog.open(EditAutomovelDialogComponent, {
      data: data,
      width: 'max-content',
      height: 'max-content',
      panelClass: '',
      enterAnimationDuration: '350ms',
      exitAnimationDuration: '350ms'
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
  }


}
