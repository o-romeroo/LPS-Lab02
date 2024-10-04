import { NotificationService } from 'src/app/service/notifications/notifications.service';
import { AutomovelService } from 'src/app/service/automovel/automovel.service';
import { Component, OnInit } from '@angular/core';
import { Automovel } from 'src/app/models/automoveis/model';
import { animate, style, transition, trigger } from '@angular/animations';
import { MatDialog } from '@angular/material/dialog';
import { CreateAutomovelDialogComponent } from './create-automovel-dialog/create-automovel-dialog.component';

@Component({
  selector: 'app-automoveis',
  templateUrl: './automoveis.component.html',
  styleUrls: ['./automoveis.component.scss'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('500ms', style({ opacity: 1 }))
      ])
    ])
  ]
})
export class AutomoveisComponent implements OnInit {


  dataSource!: Automovel[];
  toolbarTitle: string = 'Automóveis';
  userType:any = localStorage.getItem('userType');

  actions = [
    {
      icon: 'flip_to_front',
      label: 'Cadastrar',
      menu: 'Adicionar Automovel',
      menuItems: [
        {
          icon: 'add_shopping_cart',
          label: 'Cadastrar Veiculo',
          action: () => this.openCreateNewAutomovelDialog()
        },
      ],
    }
  ];
  displayedColumns: string[] = ['id', 'marca', 'modelo', 'ano', 'placa', 'valor', 'detalhes'];

  constructor(
    private automovelService: AutomovelService,
    private dialog: MatDialog,
    private notificationService:NotificationService
  ) { }


  ngOnInit(): void {
    this.automovelService.getAutomoveis().subscribe((e: any) => {
      this.dataSource = e;

      console.log(this.dataSource);
    });

    this.notificationService.automovelCriado$.subscribe(()=> this.reloadTable());
    this.notificationService.automovelDeletado$.subscribe(()=> this.reloadTable());

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
  }


  reloadTable(){
    this.automovelService.getAutomoveis().subscribe((e: any) => {
      this.dataSource = e;
    });
  }

  openCreateNewAutomovelDialog() {
    this.dialog.open(CreateAutomovelDialogComponent, {
      width: 'max-content',
      height: 'max-content',
      panelClass: '',
      enterAnimationDuration: '350ms',
      exitAnimationDuration: '350ms'
    });
  }






}
