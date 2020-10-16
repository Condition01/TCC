import { Injectable, NgModule } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AutenticacaoService } from '../services/autenticacao.service';
import { environment } from 'src/environments/environment';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private autenticacaoService: AutenticacaoService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const autenticacaoAtual = JSON.parse(
      sessionStorage.getItem('autenticacao')
    );
    const isApiUrl = request.url.startsWith(environment.apiBaseUrl);
    if (autenticacaoAtual && isApiUrl) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${autenticacaoAtual.access_token}`,
        },
      });
    }
    return next.handle(request);
  }
}
