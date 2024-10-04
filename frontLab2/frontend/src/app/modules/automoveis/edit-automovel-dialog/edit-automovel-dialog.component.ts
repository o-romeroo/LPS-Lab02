import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AutomovelService } from 'src/app/service/automovel/automovel.service';
import { NotificationService } from 'src/app/service/notifications/notifications.service';

@Component({
  selector: 'app-edit-automovel-dialog',
  templateUrl: './edit-automovel-dialog.component.html',
  styleUrls: ['./edit-automovel-dialog.component.scss']
})
export class EditAutomovelDialogComponent {
  form: FormGroup;
  marcas: any = ['Toyota', 'Honda', 'Ford', 'Chevrolet', 'Volkswagen', 'Fiat', 'Renault', 'Peugeot', 'Hyundai', 'Citroen', 'Jeep', 'Nissan', 'Mitsubishi', 'Mercedes-Benz', 'BMW', 'Audi', 'Kia', 'Volvo', 'Land Rover', 'Porsche', 'Jaguar', 'Chery', 'Subaru', 'Lifan', 'Troller', 'Agrale', 'Aston Martin', 'Bentley', 'Chrysler', 'Dodge', 'Ferrari', 'Lamborghini', 'Maserati', 'Mini', 'Rolls-Royce', 'Smart', 'Tesla', 'Lexus', 'Infiniti', 'Acura', 'Alfa Romeo', 'Bugatti', 'Cadillac', 'Caterham', 'Dacia', 'Daewoo', 'Daihatsu', 'Geely', 'Hummer', 'Isuzu', 'JAC', 'Koenigsegg', 'Lada', 'Lotus', 'Mahindra', 'Maybach', 'McLaren', 'MG', 'Pagani', 'Pontiac', 'Ram', 'Rover', 'Saab', 'Scion', 'Seat', 'SsangYong', 'Tata', 'Troller', 'TVR', 'Wuling', 'Zenvo', 'Outros'];
  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<EditAutomovelDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private snackbar:MatSnackBar,
    private automovelService: AutomovelService,
    private notificationService:NotificationService
  ) {

    this.form = this.fb.group({
      id: [this.data.id],
      placa: ['', Validators.required],
      ano: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
      modelo: ['', Validators.required],
      marca: ['', Validators.required],
      precoDiaria: ['', [Validators.required, Validators.pattern('^[0-9]*\\.?[0-9]+$')]]
    });
  }

  ngOnInit() {
    this.form.patchValue(this.data);
  }



  onUpdate() {
    this.automovelService.updateAutomovel(this.form.value).subscribe((e: any) => {
      this.snackbar.open('Automóvel atualizado com sucesso', 'Fechar', {
        duration: 2000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });

      this.notificationService.automovelCriadoEvent();
    });

      
    this.dialogRef.close(this.form.value);
  }

  onClose() {
    this.dialogRef.close();
  }
}
