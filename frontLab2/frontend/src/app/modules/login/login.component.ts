import { LoginService } from './../../service/login/login.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/service/notifications/notifications.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  loginForm: FormGroup;
  usuarioHasDeslogado: any;

  constructor(private fb: FormBuilder, private loginService: LoginService, private snackBar: MatSnackBar, private notificationService: NotificationService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });



  }
  ngOnInit(): void {

    this.notificationService.usuarioLogout$.subscribe(() => {
      this.usuarioHasDeslogado = true;
    });

    if(this.usuarioHasDeslogado){
      this.snackBar.open('Usuário deslogado com sucesso', 'Fechar', {
        duration: 2000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
    }
  }

  onLogin() {
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(
        (response) => {
          localStorage.setItem('user', response.idLogin);
          localStorage.setItem('userType', response.userType);
            this.router.navigate(['/automoveis']);
        },
        (error) => {
          if (error.status == 401) {
            this.snackBar.open('Usuário ou senha inválidos', 'Fechar', {
              duration: 2000,
              horizontalPosition: 'center',
              verticalPosition: 'top'
            });
          }
        }
      );
    }



  }
}
