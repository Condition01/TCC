import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../models/pessoa.model';
import { environment } from 'src/environments/environment';
import { map, take } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AutenticacaoService {
  private actualAutenticacaoSubject: BehaviorSubject<any>;
  public actualAutenticacao: Observable<any>;

  private actualUsuarioSubject: BehaviorSubject<Usuario>;
  public actualUsuario: Observable<Usuario>;

  private apiUrl = environment.apiBaseUrl + '/auth';

  private base64Client =
    'Basic MmJlZmE1MjYtMDliZS0xMWViLWFkYzEtMDI0MmFjMTIwMDAyOjMzMjE2ZDY2LTA5YmUtMTFlYi1hZGMxLTAyNDJhYzEyMDAwMg==';
  private grant_type = 'password';

  constructor(private http: HttpClient, private router: Router) {
    this.actualAutenticacaoSubject = new BehaviorSubject<any>(
      JSON.parse(localStorage.getItem('autenticacao'))
    );

    this.actualUsuarioSubject = new BehaviorSubject<Usuario>(
      JSON.parse(localStorage.getItem('pessoa'))
    );

    this.actualAutenticacao = this.actualAutenticacaoSubject.asObservable();
    this.actualUsuario = this.actualUsuarioSubject.asObservable();
  }

  public get actualAutenticacaoValue(): any {
    return this.actualAutenticacaoSubject.value;
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

  login(pessoa: string, senha: string) {
    let headers = this.getHeaders();
    let body = `grant_type=${this.grant_type}&username=${pessoa}&password=${senha}`;

    return this.http
      .post<any>(`${this.apiUrl}/oauth/token`, body, { headers: headers })
      .pipe(
        map((token) => {
          let autenticacao = {
            access_token: token.access_token,
            refresh_token: token.refresh_token,
          };
          sessionStorage.setItem('autenticacao', JSON.stringify(autenticacao));
          this.actualAutenticacaoSubject.next(token);
        })
      );
  }

  getUsuario() {
    return this.http
      .get<any>(`${this.apiUrl}/user`)
      .pipe(
        map((pessoa) => {
          let usuarioAchado = pessoa as Usuario;
          sessionStorage.setItem('pessoa', JSON.stringify(usuarioAchado));
          this.actualUsuarioSubject.next(pessoa);
        })
      );
  }

  novoUsuario(tokens: any) {
    let autenticacao = {
      access_token: tokens.access_token,
      refresh_token: tokens.refresh_token,
    };
    sessionStorage.setItem('autenticacao', JSON.stringify(autenticacao))
    this.actualAutenticacaoSubject.next(autenticacao);
  }

  refreshToken(): Observable<any> {
    let headers = this.getHeaders();
    const reqBody = `username=${this.actualUsuarioValue.email}&grant_type=refresh_token&refresh_token=${this.actualAutenticacaoValue.refresh_token}`;
    const finalURL = environment.apiBaseUrl + '/oauth/token';
    return this.http.post(finalURL, reqBody, { headers: headers });
  }

  logout() {
    return this.http.get(`${this.apiUrl}/token/expire`).pipe(take(1));
  }
}
