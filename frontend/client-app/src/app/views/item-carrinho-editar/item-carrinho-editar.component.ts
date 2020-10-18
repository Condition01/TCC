import { Component, OnInit, Inject } from '@angular/core';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { CarrinhoShow } from 'src/app/models/carrinho-show.model';
import { Produto } from 'src/app/models/produto.model';
import { CarrinhoService } from 'src/app/services/carrinho.service';
import { NotificacaoService } from 'src/app/services/notificacao.service';

@Component({
  selector: 'app-item-carrinho-editar',
  templateUrl: './item-carrinho-editar.component.html',
  styleUrls: ['./item-carrinho-editar.component.css'],
})
export class ItemCarrinhoEditarComponent implements OnInit {
  constructor(
    private dialogRef: MatDialogRef<ItemCarrinhoEditarComponent>,
    private carrinhoService: CarrinhoService,
    private notificacaoService: NotificacaoService,
    @Inject(MAT_DIALOG_DATA) private data: CarrinhoShow
  ) {}

  itemCarrinho: CarrinhoShow;
  quantidade: number;
  valorTotal: number;

  ngOnInit(): void {
    this.itemCarrinho = this.data as CarrinhoShow;
    this.quantidade = this.itemCarrinho.quantidade;
    this.valorTotal = this.itemCarrinho.valor;
  }

  fechar() {
    this.dialogRef.close();
  }

  editar() {
    if (this.quantidade > 0) {
      this.carrinhoService.editar(
        this.itemCarrinho.produto.codProduto,
        this.quantidade
      );
      this.fechar();
    }else {
      this.notificacaoService.normalNotification('Quantidade deve ser maior que 0');
    }
  }

  alterarPreco() {
    this.valorTotal = this.itemCarrinho.produto.preco * this.quantidade;
  }
}
