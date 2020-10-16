import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CepService {

  constructor(private http: HttpClient) { }

  pegarEndereco(cpf: string): Observable<any> {
    let cepApi = environment.cepApi
    return this.http.get(`${cepApi}/${cpf}/json/`).pipe(take(1));
  }

}
