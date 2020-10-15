import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../models/usuario.model';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AutenticacaoService {
  private actualAutorizacaoSubject: BehaviorSubject<any>;
  public actualAutorizacao: Observable<any>;

  private actualUsuarioSubject: BehaviorSubject<Usuario>;
  public actualUsuario: Observable<Usuario>;

  private apiUrl = environment.apiBaseUrl + '/auth';

  private base64Client =
    'Basic MmJlZmE1MjYtMDliZS0xMWViLWFkYzEtMDI0MmFjMTIwMDAyOjMzMjE2ZDY2LTA5YmUtMTFlYi1hZGMxLTAyNDJhYzEyMDAwMg==';
  private grant_type = 'password';

  constructor(private http: HttpClient, private router: Router) {
    this.actualAutorizacaoSubject = new BehaviorSubject<any>(
      JSON.parse(localStorage.getItem('autenticacao'))
    );

    this.actualUsuarioSubject = new BehaviorSubject<Usuario>(
      JSON.parse(localStorage.getItem('usuario'))
    );

    this.actualAutorizacao = this.actualAutorizacaoSubject.asObservable();
    this.actualUsuario = this.actualUsuarioSubject.asObservable();
  }

  public get actualAutorizacaoValue(): any {
    return this.actualAutorizacaoSubject.value;
  }

  public get actualUsuarioValue(): Usuario {
    return this.actualUsuarioSubject.value;
  }

  getHeaders(): HttpHeaders {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.set('Authorization', this.base64Client);
    headers = headers.set('Content-Type', 'application/x-www-form-urlencoded');
    return headers;
  }

  login(usuario: string, senha: string) {
    let headers = this.getHeaders();
    let body = `grant_type=${this.grant_type}&username=${usuario}&password=${senha}`;
    return this.http
      .post<any>(`${this.apiUrl}/oauth/token`, body, { headers: headers })
      .pipe(
        map((token) => {
          let autenticacao = {
            access_token: token.access_token,
            refresh_token: token.refresh_token,
          };
          sessionStorage.setItem('autenticacao', JSON.stringify(autenticacao));
          this.actualAutorizacaoSubject.next(token);
        })
      );
  }

  getUsuario() {
    let autenticacao = JSON.parse(sessionStorage.getItem('autenticacao'));
    let headers = new HttpHeaders();

    headers = headers.set(
      'Authorization', `Bearer ${autenticacao.access_token}`
    );

    return this.http
      .get<any>(`${this.apiUrl}/user`, { headers: headers })
      .pipe(
        map((usuario) => {
          let usuarioAchado = usuario as Usuario;
          sessionStorage.setItem('usuario', JSON.stringify(usuarioAchado));
          this.actualUsuarioSubject.next(usuario);
        })
      );
  }

  refreshToken(): Observable<any> {
    let headers = this.getHeaders();
    const reqBody = `username=${this.actualUsuarioValue.email}&grant_type=refresh_token&refresh_token=${this.actualAutorizacaoValue.refresh_token}`;
    const finalURL = environment.apiBaseUrl + '/oauth/token';
    return this.http.post(finalURL, reqBody, { headers: headers });
  }

  logout() {
    this.router.navigate(['/']);
  }
}
