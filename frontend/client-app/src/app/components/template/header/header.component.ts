import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario.model';
import { AutenticacaoService } from 'src/app/services/autenticacao.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  url: string;
  usuario: Usuario;
  autenticacao: any;

  constructor(
    private router: Router,
    private autenticacaoService: AutenticacaoService
  ) {
    this.url = this.router.url;
  }

  ngOnInit(): void {
    this.login();
  }

  login() {
    this.autenticacao = JSON.parse(sessionStorage.getItem('autenticacao'));

    if (this.autenticacao) {
      this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    }
  }

  deslogar() {
    this.autenticacaoService.logout().subscribe((logout) => {
      sessionStorage.clear();
      this.usuario = null;
      this.autenticacao = null;
      this.router.navigate(['/']);
    });
  }
}
