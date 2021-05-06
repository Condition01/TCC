import { Injectable } from '@angular/core';
import { Pessoa } from '../models/pessoa.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private apiUrl = environment.apiBaseUrl + '/auth'

  constructor(private http: HttpClient) {

   }

  cadastrar(pessoa: Pessoa): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    return this.http.post(`${this.apiUrl}/user`, pessoa, {headers: headers}).pipe(take(1));
  }

}
