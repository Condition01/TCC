import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Produto } from 'src/app/models/produto.model';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor() { }

  produtos: Produto[] = [
    {id: "1", nome: 'Mamao', preco: 10.22, descricao: "É uma fruta deliciosa", unidade: "Kilo", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "2", nome: 'Tomate', preco: 5.30, descricao: "É uma fruta deliciosa", unidade: "Duzia", imgUrl:
     "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "3", nome: 'Abacaixi', preco: 7.82, descricao: "É uma fruta deliciosa", unidade: "Cada", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "4", nome: 'Abacate', preco: 14.52, descricao: "É uma fruta deliciosa", unidade: "Cada", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "5", nome: 'Limão', preco: 12.33, descricao: "É uma fruta deliciosa", unidade: "Kilo", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "6", nome: 'Laranja', preco: 1.30, descricao: "É uma fruta deliciosa", unidade: "1/2 Duzia", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "7", nome: 'Melância', preco: 11.00, descricao: "É uma fruta deliciosa", unidade: "Cada", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "8", nome: 'Couve', preco: 9.20, descricao: "É uma fruta deliciosa", unidade: "Pé", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "9", nome: 'Cacau', preco: 5.00, descricao: "É uma fruta deliciosa", unidade: "Cada", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},
    {id: "10", nome: 'Amendoim', preco: 4.00, descricao: "É uma fruta deliciosa", unidade: "Kilo", imgUrl: 
    "https://images.squarespace-cdn.com/content/v1/5b8edfa12714e508f756f481/1538675155980-3TFCCGAIDUCP5HCKE9QP/ke17ZwdGBToddI8pDm48kLSAqPnO-7rDxwHsKNCpcntZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwgxCZ2Ngoxh0xS1rcyD8Qk-mknpTvzODl94OXWpaLgsLqHLHIN2crfyxoL1QnBJLM/image-asset.jpeg"},

  ]

  listarProdutos(): Observable<Produto[]> {
    return of(this.produtos);
  }

  resgatarProduto(id: string): Observable<Produto> {
    console.log(id)
    let produtoResgatado = this.produtos.find(produto => {
      if(produto.id == id){
        return produto
      }
    })
    return of(produtoResgatado)
  }

}
