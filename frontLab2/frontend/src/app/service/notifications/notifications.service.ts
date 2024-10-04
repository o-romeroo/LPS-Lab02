// notification.service.ts
import { Injectable } from '@angular/core';
import { MatTableDataSource, MatTableDataSourcePaginator } from '@angular/material/table';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {

  public automovelCriado = new Subject<boolean>();
  public automovelDeletado = new Subject<boolean>();

  public contratoCriado = new Subject<boolean>();
  public contratoDeletado = new Subject<boolean>();

  public contratoAprovado = new Subject<boolean>();
  public contratoRejeitado = new Subject<boolean>();

  public usuarioLogout = new Subject<boolean>();


  automovelCriado$ = this.automovelCriado.asObservable();
  automovelDeletado$ = this.automovelDeletado.asObservable();

  contratoCriado$ = this.contratoCriado.asObservable();
  contratoDeletado$ = this.contratoDeletado.asObservable();

  contratoAprovado$ = this.contratoAprovado.asObservable();
  contratoRejeitado$ = this.contratoRejeitado.asObservable();


  usuarioLogout$ = this.usuarioLogout.asObservable();

  constructor() { }

  
  automovelCriadoEvent() {
    this.automovelCriado.next(true);
  }

  automovelDeletadoEvent() {
    this.automovelDeletado.next(true);
  }

  contratoCriadoEvent() {
    this.contratoCriado.next(true);
  }

  contratoDeletadoEvent() {
    this.contratoDeletado.next(true);
  }

  usuarioLogoutEvent(a:any) {
    this.usuarioLogout.next(a);
  }

  contratoAprovadoEvent() {
    this.contratoAprovado.next(true);
  }

  contratoRejeitadoEvent() {
    this.contratoRejeitado.next(true);
  }

}
