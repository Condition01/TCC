import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CarrinhoShow } from 'src/app/models/carrinho-show.model';
import { Pagamento } from 'src/app/models/pagamento.mode';
import { ProdutoQuantidade } from 'src/app/models/produto-quantidade';
import { CarrinhoService } from 'src/app/services/carrinho.service';
import { NotificacaoService } from 'src/app/services/notificacao.service';

@Component({
  selector: 'app-pagamento',
  templateUrl: './pagamento.component.html',
  styleUrls: ['./pagamento.component.scss'],
})
export class PagamentoComponent implements OnInit {
  form: FormGroup;
  produtos: CarrinhoShow[];

  constructor(
    private formBuilder: FormBuilder,
    private notificacaoService: NotificacaoService,
    private carrinhoService: CarrinhoService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({});
    this.produtos = this.carrinhoService.mostrarCarrinho();
    this.produtos.forEach((carrinhoShow) => {
      this.calcularPreco(carrinhoShow);
    });
  }

  calcularPreco(item: CarrinhoShow) {
    item.valor = item.produto.preco * item.quantidade;
  }

  valor(prod: CarrinhoShow) {
    return prod.quantidade * prod.produto.preco;
  }

  pegarCustoTotal() {
    console.log(this.produtos)
    if (this.produtos.length !== 0) {
      return this.produtos
        .map((t) => t.valor)
        .reduce((acc, value) => acc + value, 0);
    }
  }

  montarPedido() {

  }

  

}
