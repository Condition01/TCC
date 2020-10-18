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

  addItem(codProduto: number, quantidade: number) {
    let cookie = this.cookieService.get('carrinho-cookie');
    let carrinho = cookie ? JSON.parse(cookie) as Map<number, number> : new Map<number, number>()
    console.log("quantidade = " + quantidade)
    if(carrinho[codProduto]){
      carrinho[codProduto] = Number(carrinho[codProduto]) + Number(quantidade);
    }else{
      carrinho[codProduto] = Number(quantidade);
    }
    console.log(carrinho)
    this.cookieService.set('carrinho-cookie', JSON.stringify(carrinho));
  }

  pegarCarrinho(): Map<number, number> {
    let cookie = this.cookieService.get('carrinho-cookie');
    return cookie ? JSON.parse(cookie) as Map<number, number> : new Map<number, number>();
  }

  mostrarCarrinho(): CarrinhoShow[] {
    let cookie = this.cookieService.get('carrinho-cookie');
    let carrinhoMap = cookie ? JSON.parse(cookie) as Map<number, number> : new Map<number, number>();
    return this.produtoService.resgatarProdutos(carrinhoMap);
  }

  editar(codProduto: number, quantidade: number) {
    let cookie = this.cookieService.get('carrinho-cookie');
    let carrinhoMap = cookie ? JSON.parse(cookie) as Map<number, number> : new Map<number, number>();
    if(carrinhoMap[codProduto]){
      console.log('passou aqui também')
      carrinhoMap[codProduto] = quantidade;
    }
    this.cookieService.set('carrinho-cookie', JSON.stringify(carrinhoMap));
  }

  excluir(codProduto: number) {
    let cookie = this.cookieService.get('carrinho-cookie');
    let carrinhoMap = cookie ? JSON.parse(cookie) as Map<number, number> : new Map<number, number>();
    // Object.keys(carrinhoMap).forEach( (key) => {
    //   delete carrinhoMap[codProduto];
    //  });
    console.log('passou aqui no excluir');
    console.log(codProduto);
    delete carrinhoMap[codProduto];
    this.cookieService.set('carrinho-cookie', JSON.stringify(carrinhoMap));
  }

}
