import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Automovel } from 'src/app/models/automoveis/model';
import { automoveis } from 'src/app/models/automoveis/data';



@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.baseUrl}usuario/login`, {
      login:username,
      senha:password
    });
  }

  
}
