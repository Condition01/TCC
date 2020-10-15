import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AutenticacaoService } from '../services/autenticacao.service';
import { environment } from 'src/environments/environment';



@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private autenticacaoService: AutenticacaoService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const autenticacaoAtual = this.autenticacaoService.actualAutorizacaoValue;
        const usuarioAtual = this.autenticacaoService.actualUsuarioValue;
        const isLoggedIn = usuarioAtual && autenticacaoAtual.access_token;
        const isApiUrl = request.url.startsWith(environment.apiBaseUrl);
        if (isLoggedIn && isApiUrl) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${autenticacaoAtual.access_token}`
                }
            });
        }
        return next.handle(request);
    }
}