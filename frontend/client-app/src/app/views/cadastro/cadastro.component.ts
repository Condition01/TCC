import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Role, Usuario } from 'src/app/models/pessoa.model';
import { AutenticacaoService } from 'src/app/services/autenticacao.service';
import { CarrinhoService } from 'src/app/services/carrinho.service';
import { CepService } from 'src/app/services/cep.service';
import { NotificacaoService } from 'src/app/services/notificacao.service';
import { UsuarioService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss'],
})
export class CadastroComponent implements OnInit {
  form: FormGroup;
  pessoa: Usuario;
  reload: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private usuarioService: UsuarioService,
    private cepService: CepService,
    private notificacaoService: NotificacaoService,
    private authService: AutenticacaoService
  ) {}

  ngOnInit(): void {
    // if (!this.reload) {
    this.form = this.formBuilder.group({
      email: [null, [Validators.required]],
      cpf: [null, [Validators.required]],
      nome: [null, [Validators.required]],
      sobrenome: [null, [Validators.required]],
      dataNasc: [null, [Validators.required]],
      senha: [null, [Validators.required]],
      cep: [null, [Validators.required]],
      cidade: [null, [Validators.required]],
      logradouro: [null, [Validators.required]],
      bairro: [null, [Validators.required]],
      numero: [null, [Validators.required]],
      complemento: [null],
    });
    // }
  }

  formParaUsuario() {
    return {
      email: this.form.get('email').value,
      cpf: this.form.get('cpf').value,
      nome: this.form.get('nome').value,
      sobrenome: this.form.get('sobrenome').value,
      dataNasc: this.form.get('dataNasc').value,
      endereco: {
        cep: this.form.get('cep').value,
        cidade: this.form.get('cidade').value,
        logradouro: this.form.get('logradouro').value,
        bairro: this.form.get('bairro').value,
        numero: this.form.get('numero').value,
        complemento: this.form.get('complemento').value,
      },
      senha: this.form.get('senha').value,
    } as Usuario;
  }

  cadastrar() {
    let pessoa = this.formParaUsuario();
    this.usuarioService.cadastrar(pessoa).subscribe(
      (success) => {
        this.notificacaoService.sucessNotification('Cadastrado com sucesso!');
        this.authService
          .login(pessoa.email, pessoa.senha)
          .subscribe((success) => {
            this.authService.getUsuario().subscribe((user) => {
              this.notificacaoService.sucessNotification('Logado com sucesso!');
              this.router.navigate(['/']);
            });
          });
      },
      (err) => {
        this.notificacaoService.errorNotification('Erro ao cadastrar usuário!');
      }
    );
  }

  findCep() {
    let cep = this.form.get('cep').value;
    return this.cepService.pegarEndereco(cep).subscribe((endereco) => {
      console.log(endereco);
      this.form.patchValue({
        cidade: endereco.localidade,
        logradouro: endereco.logradouro,
        bairro: endereco.bairro,
      });
    });
  }
}
