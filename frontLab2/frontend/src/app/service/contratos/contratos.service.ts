import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ContratoService {

  private baseUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }


  getContratosByStatus(status:any): any {
    return this.http.get<any>('http://localhost:8080/api/contratos/empresa/' + localStorage.getItem('user')+'/status/'+status);
  }


  approveContrato(idContrato:any): any {
    return this.http.post<any>('http://localhost:8080/api/contratos/'+idContrato+'/approve?empresaId='+localStorage.getItem('user'), {});
  }

  rejectContrato(idContrato:any): any {
    return this.http.post<any>('http://localhost:8080/api/contratos/'+idContrato+'/reject?empresaId='+localStorage.getItem('user'), {});
  }

  

  
}
