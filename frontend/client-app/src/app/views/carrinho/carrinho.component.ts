import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { CarrinhoShow } from 'src/app/models/carrinho-show.model';
import { Produto } from 'src/app/models/produto.model';
import { CarrinhoService } from 'src/app/services/carrinho.service';
import { ItemCarrinhoEditarComponent } from '../item-carrinho-editar/item-carrinho-editar.component';

export interface Transaction {
  item: string;
  cost: number;
}

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.scss'],
})
export class CarrinhoComponent implements OnInit {
  displayedColumns = ['item', 'quantidade', 'valor', 'Acao'];
  produtoCarrinho: CarrinhoShow[];
  /** Gets the total cost of all transactions. */

  constructor(
    private carrinhoService: CarrinhoService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.mostrarCarrinho();
  }

  mostrarCarrinho() {
    this.produtoCarrinho = this.carrinhoService.mostrarCarrinho();
    this.produtoCarrinho.forEach((carrinhoShow) => {
      this.calcularPreco(carrinhoShow);
    });
  }

  calcularPreco(item: CarrinhoShow) {
    item.valor = item.produto.preco * item.quantidade;
  }

  excluir(codProduto: number) {
    this.carrinhoService.excluir(codProduto);
    this.mostrarCarrinho();
  }

  editar(codProduto: number) {
    console.log(codProduto);
  }

  pegarTotalDeItens() {
    if (this.tamanhoCarrinho() !== 0) {
      return this.produtoCarrinho
        .map((t) => t.quantidade)
        .reduce((acc, value) => acc + value);
    }
  }

  pegarCustoTotal() {
    if (this.tamanhoCarrinho() !== 0) {
      return this.produtoCarrinho
        .map((t) => t.valor)
        .reduce((acc, value) => acc + value, 0);
    }
  }

  tamanhoCarrinho(): number {
    let size = 0;
    this.produtoCarrinho.forEach((prod) => {
      size += 1;
    });
    return size;
  }

  // finalizarCompra() {
  //   this.router.navigate(['pagamento']);
  // }

  abrirEdicao(carrinhoProd: CarrinhoShow): void {
    const dialogRef = this.dialog.open(ItemCarrinhoEditarComponent, {
      width: '250px',
      data: carrinhoProd
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.mostrarCarrinho();
    });
  }

}
