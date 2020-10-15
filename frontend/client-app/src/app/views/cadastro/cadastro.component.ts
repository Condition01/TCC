import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CarrinhoService } from 'src/app/services/carrinho.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss'],
})
export class CadastroComponent implements OnInit {
  // produto: Produto;
  // quantidade: number;
  // form: FormGroup

  // ngOnInit(): void {
  //   let id = this.route.snapshot.paramMap.get('id');
  //   this.productService.resgatarProduto(id).subscribe(
  //     (produto) => {
  //       console.log(produto)
  //       this.produto = produto
  //       this.form = this.formBuilder.group({
  //         quantidade: [1, [Validators.required]]
  //       })
  //     },
  //     (err) => {
  //       console.log(err)
  //     }
  //   );
  // }

  form: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private carrinhoService: CarrinhoService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: [null, [Validators.required]],
      cpf: [null, [Validators.required]],
      nome: [null, [Validators.required]],
      sobrenome: [null, [Validators.required]],
      dataNasc: [null, [Validators.required]],
      senha: [null, [Validators.required]],
      cep: [null, [Validators.required]],
      logradouro: [null, [Validators.required]],
      bairro: [null, [Validators.required]],
      numero: [null, [Validators.required]],
      complemento: [null, [Validators.required]],
    });
  }

  cadastrar() {}

  findCep(cep: string) {
    
  }
}
