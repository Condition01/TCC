import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarrinhoComponent } from './views/carrinho/carrinho.component';
import { LoginComponent } from './views/login/login.component';
import { PagamentoComponent } from './views/pagamento/pagamento.component';
import { ProdutoComprarComponent } from './views/produto-comprar/produto-comprar.component';
import { ProdutoListarComponent } from './views/produto-listar/produto-listar.component';

const routes: Routes = [
  {
    path: '',
    component: ProdutoListarComponent
  },
  {
    path: 'comprar/:id',
    component: ProdutoComprarComponent
  },
  {
    path: 'carrinho',
    component: CarrinhoComponent
  },
  {
    path: 'pagamento',
    component: PagamentoComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: "**",
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
