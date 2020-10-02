import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Produto } from 'src/app/models/produto.model';
import { CarrinhoShow } from '../models/carrinho-show.model';
import { ProdutoService } from './produto.service';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {

  constructor(private cookieService: CookieService, private produtoService: ProdutoService) { }

  addItem(prodId: string) {
    let cookie = this.cookieService.get('carrinho-cookie');
    let carrinho = cookie ? JSON.parse(cookie) as Map<string, number> : new Map<string, number>()
    if(carrinho[prodId]){
      carrinho[prodId] = carrinho[prodId] + 1;
    }else{
      carrinho[prodId] = 1;
    }
    console.log(carrinho)
    this.cookieService.set('carrinho-cookie', JSON.stringify(carrinho));
  }

  pegarCarrinho(): Map<string, number> {
    let cookie = this.cookieService.get('carrinho-cookie');
    return cookie ? JSON.parse(cookie) as Map<string, number> : new Map<string, number>();
  }

  mostrarCarrinho(): CarrinhoShow[] {
    let cookie = this.cookieService.get('carrinho-cookie');
    let carrinhoMap = cookie ? JSON.parse(cookie) as Map<string, number> : new Map<string, number>();
    return this.produtoService.resgatarProdutos(carrinhoMap)
  }

  editar() {

  }

  excluir(id: string) {
    let cookie = this.cookieService.get('carrinho-cookie');
    let carrinhoMap = cookie ? JSON.parse(cookie) as Map<string, number> : new Map<string, number>();
    Object.keys(carrinhoMap).forEach( (key) => {
      delete carrinhoMap[key];
     });
    this.cookieService.set('carrinho-cookie', JSON.stringify(carrinhoMap));
  }

}
