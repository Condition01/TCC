import { Injectable, NgModule } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS,
  HttpResponse,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AutenticacaoService } from '../services/autenticacao.service';
import { environment } from 'src/environments/environment';
import { catchError, map, switchMap } from 'rxjs/operators';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private autenticacaoService: AutenticacaoService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err.status === 401) {
          return this.autenticacaoService.refreshToken().pipe(
            switchMap((tokens: any) => {
              this.autenticacaoService.novoPessoa(tokens);
              return next.handle(request);
            })
          );
        } else {
          throw err;
        }
      })
    );
  }
}
