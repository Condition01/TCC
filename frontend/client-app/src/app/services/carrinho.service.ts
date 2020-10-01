import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Produto } from 'src/app/models/produto.model';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {

  constructor(private cookieService: CookieService) { }

  addItem(prodId: string) {
    let cookie = this.cookieService.get('carrinho-cookie');
    let carrinho = cookie ? JSON.parse(cookie) as Map<string, number> : new Map<string, number>()
    if(carrinho[prodId]){
      carrinho[prodId] = carrinho[prodId] + 1
    }else{
      carrinho[prodId] = 1
    }
    console.log(carrinho)
    this.cookieService.set('carrinho-cookie', JSON.stringify(carrinho))
  }

  pegarCarrinho(): Map<string, number> {
    let cookie = this.cookieService.get('carrinho-cookie');
    return cookie ? JSON.parse(cookie) as Map<string, number> : new Map<string, number>()
  }

}
