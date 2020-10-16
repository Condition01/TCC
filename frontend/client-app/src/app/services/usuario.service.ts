import { Injectable } from '@angular/core';
import { Usuario } from '../models/usuario.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = environment.apiBaseUrl + '/auth'

  constructor(private http: HttpClient) {

   }

  cadastrar(usuario: Usuario): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    return this.http.post(`${this.apiUrl}/user`, usuario, {headers: headers}).pipe(take(1));
  }
  
}
