import { AutomovelService } from 'src/app/service/automovel/automovel.service';
import { Component, OnInit } from '@angular/core';
import { Automovel } from 'src/app/models/automoveis/model';
import { Contrato } from 'src/app/models/contratos/model';
import { ContratoService } from 'src/app/service/contratos/contratos.service';
import { animate, style, transition, trigger } from '@angular/animations';
import { NotificationService } from 'src/app/service/notifications/notifications.service';
import { MatDialog } from '@angular/material/dialog';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-contratos',
  templateUrl: './contratos.component.html',
  styleUrls: ['./contratos.component.scss'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('500ms', style({ opacity: 1 }))
      ])
    ])
  ]
})
export class ContratosComponent implements OnInit {


  dataSourcePendentes: any;
  dataSourceAtivos: any;
  dataSourceFinalizados: any;
  dataSourceCancelados: any;
  userType:any = localStorage.getItem('userType');

  toolbarTitle: string = 'Contratos';

  constructor(
    private contratoService: ContratoService,
    private dialog: MatDialog,
    private notificationService: NotificationService
  ) { }

  ngOnInit(): void {

    this.notificationService.contratoCriado$.subscribe(() => this.reloadTable());
    this.notificationService.contratoDeletado$.subscribe(() => this.reloadTable());
    this.notificationService.contratoAprovado$.subscribe(() => this.reloadTable());
    this.notificationService.contratoRejeitado$.subscribe(() => this.reloadTable());

    forkJoin([
      this.contratoService.getContratosByStatus('PENDENTE'),
      this.contratoService.getContratosByStatus('ATIVO'),
      this.contratoService.getContratosByStatus('FINALIZADO'),
      this.contratoService.getContratosByStatus('CANCELADO')
    ]).subscribe((e: any) => {
      this.dataSourcePendentes = e[0];
      this.dataSourceAtivos = e[1];
      this.dataSourceFinalizados = e[2];
      this.dataSourceCancelados = e[3];
    });

  }

  reloadTable() {
    forkJoin([
      this.contratoService.getContratosByStatus('PENDENTE'),
      this.contratoService.getContratosByStatus('ATIVO'),
      this.contratoService.getContratosByStatus('FINALIZADO'),
      this.contratoService.getContratosByStatus('CANCELADO')
    ]).subscribe((e: any) => {
      this.dataSourcePendentes = e[0];
      this.dataSourceAtivos = e[1];
      this.dataSourceFinalizados = e[2];
      this.dataSourceCancelados = e[3];
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
  }



}
