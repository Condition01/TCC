import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/template/footer/footer.component';
import { HeaderComponent } from './components/template/header/header.component';
import { NavComponent } from './components/template/nav/nav.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//material
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { TopMenuComponent } from './components/template/top-menu/top-menu.component';
import { MatIconModule } from '@angular/material/icon';
import { MatRippleModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { ProdutoListarComponent } from './views/produto-listar/produto-listar.component';
import { ProdutoComprarComponent } from './views/produto-comprar/produto-comprar.component';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider'

import {MatGridListModule} from '@angular/material/grid-list';
import { MatListModule } from '@angular/material/list';

import { CookieService } from 'ngx-cookie-service'

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    NavComponent,
    TopMenuComponent,
    ProdutoListarComponent,
    ProdutoComprarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatRippleModule,
    MatButtonModule,
    MatCardModule,
    MatGridListModule,
    MatDividerModule,
    MatListModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent],
})
export class AppModule {}
