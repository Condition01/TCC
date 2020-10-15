import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class NotificacaoService {

  constructor(private stackBar: MatSnackBar) { }

  sucessNotification(messagem) {
    this.stackBar.open(messagem, 'FECHAR', {
      duration: 2000,
      panelClass: ['mat-sucess', 'success-bar']
    });
  }

  errorNotification(messagem) {
    this.stackBar.open(messagem, 'FECHAR', {
      duration: 2000,
      panelClass: ['mat-accent', 'error-bar']
    });
  }

  normalNotification(messagem) {
    this.stackBar.open(messagem, 'FECHAR', {
      duration: 2000,
    });
  }

}
