import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-detalhes-dialog',
  templateUrl: './detalhes-dialog.component.html',
  styleUrls: ['./detalhes-dialog.component.scss']
})
export class DetalhesDialogComponent implements OnInit {
  
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  contrato:any;

  ngOnInit(): void {
    this.contrato = this.data;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
  }

}
