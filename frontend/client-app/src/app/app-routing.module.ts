import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProdutoComprarComponent } from './views/produto-comprar/produto-comprar.component';
import { ProdutoListarComponent } from './views/produto-listar/produto-listar.component';

const routes: Routes = [
  {
    path: "",
    component: ProdutoListarComponent
  },
  {
    path: "comprar/:id",
    component: ProdutoComprarComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
