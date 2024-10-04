import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Contrato } from 'src/app/models/contratos/model';
import { NotificationService } from 'src/app/service/notifications/notifications.service';
import { DetalhesDialogComponent } from './detalhes-dialog/detalhes-dialog.component';
import { ContratoService } from 'src/app/service/contratos/contratos.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-table-pagination-contratos',
  templateUrl: './table-pagination-contratos.component.html',
  styleUrls: ['./table-pagination-contratos.component.scss']
})
export class TablePaginationContratosComponent implements OnInit {


  @Input() dataSource!: any;
  /** export interface Contrato {
  nomeCliente: string;
  placa: string;
  cpf: string;
  modelo: string;
  preço: number;
  metodo_pagamento: boolean;
} */
  displayedColumns: string[] = ['id', 'nomeCliente', 'cpf', 'placa', 'modelo', 'valor','diarias', 'detalhes'];

  constructor(
    private dialog: MatDialog,
    private notificationService: NotificationService,
    private contratoService: ContratoService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.notificationService.contratoCriado$.subscribe(() => this.reloadTable());
    this.notificationService.contratoDeletado$.subscribe(() => this.reloadTable());
  }

  reloadTable() {
    //this.dataSource.paginator = this.paginator;
    //this.dataSource.sort = this.sort;
  }


  openDetalhesDialog(contrato: any) {
    this.dialog.open(DetalhesDialogComponent, {
      data: contrato,
      width: 'max-content',
      height: 'max-content',
      panelClass: '',
      enterAnimationDuration: '350ms',
      exitAnimationDuration: '350ms'
    });
  }



  approveContrato(contratoId:any){
    this.contratoService.approveContrato(contratoId).subscribe((data: any) => {
      this.snackBar.open('Contrato Aprovado com sucesso!', 'Fechar', {
        duration: 2000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });

      this.notificationService.contratoAprovadoEvent();
    });
  }

  rejectContrato(contrtoId:any){
    this.contratoService.rejectContrato(contrtoId).subscribe((data: any) => {
      this.snackBar.open('Contrato Rejeitado com sucesso!', 'Fechar', {
        duration: 2000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });

      this.notificationService.contratoRejeitadoEvent();
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
  }








}
