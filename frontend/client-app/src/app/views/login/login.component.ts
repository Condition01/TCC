import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { AutenticacaoService } from 'src/app/services/autenticacao.service';
import { NotificacaoService } from 'src/app/services/notificacao.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  bannerSubject: Subject<any> = new Subject<any>();

  constructor(
    private authService: AutenticacaoService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private notificacaoService: NotificacaoService
  ) {}

  form: FormGroup;

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      login: [null, [Validators.required]],
      senha: [null, [Validators.required]],
    });
  }

  logar() {
    let login = this.form.get('login').value;
    let senha = this.form.get('senha').value;
    if (login && senha) {
      this.authService.login(login, senha).subscribe((login) => {
        this.authService.getUsuario().subscribe(
          (user) => {
            this.notificacaoService.sucessNotification('Logado com sucesso!');
            this.router.navigate(['/']);
          }
        );
      },
      (err) => {
        if(err.error.error_description.includes('inexistente ou senha')){
          this.notificacaoService.normalNotification('Usuario ou Senha Inválidos');
        }else{
          this.notificacaoService.normalNotification('erro desconhecido!!');
        }

      });
    } else {
      this.notificacaoService.normalNotification(
        'É necessario digitar o Usuario e Senha'
      );
    }
    // this.notificacaoService.errorNotification('Cadastro Efetuado!')
  }
}
