import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produto } from 'src/app/models/produto.model';
import { ProdutoService } from 'src/app/services/produto.service';
import { MatIcon, MatIconModule } from '@angular/material/icon'
import { CarrinhoService } from 'src/app/services/carrinho.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotificacaoService } from 'src/app/services/notificacao.service';


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
    private formBuilder: FormBuilder,
    private notificacaoService: NotificacaoService,
    private carrinhoService: CarrinhoService
  ) {}

  produto: Produto;
  quantidade: number;
  form: FormGroup

  ngOnInit(): void {
    let codProduto = this.route.snapshot.paramMap.get('codProduto');
    this.productService.resgatarProduto(+codProduto).subscribe(
      (produto) => {
        console.log(produto)
        this.produto = produto
        this.form = this.formBuilder.group({
          quantidade: [1, [Validators.required]]
        })
      },
      (err) => {
        console.log(err)
      }
    );
  }

  adicionarCarrinho(codProduto: number) {
    console.log(codProduto);
    this.quantidade = this.form.get('quantidade').value;
    if(this.quantidade > 0){
      this.carrinhoService.addItem(codProduto, this.quantidade);
      this.notificacaoService.sucessNotification('Item adicionado com sucesso!')
      this.router.navigate(['']);
    }else{
      this.notificacaoService.normalNotification('Quantidade deve ser maior que 0');
    }
  }

}
