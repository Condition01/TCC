import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Produto, TipoProduto } from 'src/app/models/produto.model';
import { CarrinhoShow } from '../models/carrinho-show.model';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  constructor() {}

  produtos: Produto[] = [
    {
      codProduto: 1,
      nome: 'Mamão Papaia',
      preco: 10.22,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 2,
      nome: 'Tomate Berlim',
      preco: 5.3,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 3,
      nome: 'Abacaxi Rei',
      preco: 7.82,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 4,
      nome: 'Abacate Cond',
      preco: 14.52,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 5,
      nome: 'Limão Siciliano',
      preco: 12.33,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 6,
      nome: 'Laranja Stupy',
      preco: 1.3,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 7,
      nome: 'Melancia',
      preco: 11.0,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 8,
      nome: 'Couve',
      preco: 9.2,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 9,
      nome: 'Cacau',
      preco: 5.0,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
    {
      codProduto: 10,
      nome: 'Amendoim',
      preco: 4.0,
      tp_Produto: TipoProduto.FRUTA,
      descricao: 'É uma fruta deliciosa',
      imgUrl:
        'https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg',
    },
  ];

  listarProdutos(): Observable<Produto[]> {
    return of(this.produtos);
  }

  resgatarProduto(codProduto: number): Observable<Produto> {
    return of(this.getProd(codProduto));
  }

  resgatarProdutos(items: Map<number, number>): CarrinhoShow[] {
    let listCarrinhoShow: CarrinhoShow[] = [];
    let that = this;
    Object.keys(items).forEach(function (key) {
      let produto = that.getProd(+key);
      console.log(produto);
      listCarrinhoShow.push({ produto: produto, quantidade: items[key] });
    });
    return listCarrinhoShow;
  }

  getProd(codProduto: number): Produto {
    let produtoResgatado = this.produtos.find((produto) => {
      if (produto.codProduto == codProduto) {
        return produto;
      }
    });
    return produtoResgatado;
  }
}
