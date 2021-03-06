import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produto } from 'src/app/models/produto.model';
import { ProdutoService } from 'src/app/services/produto.service';
import { MatIcon, MatIconModule } from '@angular/material/icon'
import { CarrinhoService } from 'src/app/services/carrinho.service';


@Component({
  selector: 'app-produto-comprar',
  templateUrl: './produto-comprar.component.html',
  styleUrls: ['./produto-comprar.component.scss'],
})
export class ProdutoComprarComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private productService: ProdutoService,
    private router: Router,
    private carrinhoService: CarrinhoService
  ) {}

  produto: Produto;
  quantidade: number = 0;

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    this.productService.resgatarProduto(id).subscribe(
      (produto) => {
        console.log(produto)
        this.produto = produto
      },
      (err) => {
        console.log(err)
      }
    );
  }

  adicionarCarrinho(id: string) {
    this.carrinhoService.addItem(id);
    this.router.navigate[''];
  }

}
