import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Produto } from 'src/app/models/produto.model';
import { AutenticacaoService } from 'src/app/services/autenticacao.service';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-produto-listar',
  templateUrl: './produto-listar.component.html',
  styleUrls: ['./produto-listar.component.scss'],
})
export class ProdutoListarComponent implements OnInit {
  constructor(private router: Router, private produtoService: ProdutoService, private authService: AutenticacaoService) {}

  produtos: Produto[]

  ngOnInit(): void {
    this.list()
  }

  list() {
    return this.produtoService.listarProdutos().subscribe(
      (prods) => {
        console.log(prods)
        this.produtos = prods
      },
      (err) => {
        console.log(err)
      }
    );
  }

  comprar(codProduto: number) {
    this.router.navigate([`comprar/${codProduto}`])
  }

}
