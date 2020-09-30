import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { ProdutoComprarComponent } from './views/produto-comprar/produto-comprar.component';
import { ProdutoListarComponent } from './views/produto-listar/produto-listar.component';

const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "produtos",
    component: ProdutoListarComponent
  },
  {
    path: "produto/:id",
    component: ProdutoComprarComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
