import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/pessoa.model';
import { AutenticacaoService } from 'src/app/services/autenticacao.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  url: string;
  pessoa: Usuario;
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
      this.pessoa = JSON.parse(sessionStorage.getItem('pessoa'));
    }
  }

  deslogar() {
    this.autenticacaoService.logout().subscribe((logout) => {
      sessionStorage.clear();
      this.pessoa = null;
      this.autenticacao = null;
      this.router.navigate(['/']);
    });
  }
}
