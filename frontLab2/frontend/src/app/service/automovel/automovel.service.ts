import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Automovel } from 'src/app/models/automoveis/model';
import { automoveis } from 'src/app/models/automoveis/data';



@Injectable({
  providedIn: 'root'
})
export class AutomovelService {

  private baseUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }


  getAutomoveis(): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/automoveis/empresa/' + localStorage.getItem('user'));
  }


  getAllVeiculos(): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/automoveis/available');
  }

  createAutomovel(automovel: any): Observable<any> {
    let request = {
      "automovel": {
        "placa": automovel.placa,
        "ano": automovel.ano,
        "marca": automovel.marca,
        "modelo": automovel.modelo,
        "precoDiaria": automovel.precoDiaria
      },
      "empresaId": localStorage.getItem('user')
    }
    
    return this.http.post<any>('http://localhost:8080/api/automoveis/add', request, { responseType: 'json' });
  }

  updateAutomovel(automovel: any): Observable<any> {
    console.log(automovel)
    let request = {
        "id": automovel.id,
        "ano": automovel.ano,
        "marca": automovel.marca,
        "modelo": automovel.modelo,
        "placa": automovel.placa,
        "precoDiaria": automovel.precoDiaria
    }

   console.log(request);

    return this.http.put<any>('http://localhost:8080/api/automoveis/update?empresaId='+localStorage.getItem('user'),request, { responseType: 'json' });
  }

  deleteAutomovel(id: any): Observable<any> {
    return this.http.delete('http://localhost:8080/api/automoveis/delete/' + id + '?empresaId=' + localStorage.getItem('user'), { responseType: 'text' });
  }



}
