import { Component, OnInit, Inject } from '@angular/core';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { CarrinhoShow } from 'src/app/models/carrinho-show.model';
import { Produto } from 'src/app/models/produto.model';

@Component({
  selector: 'app-item-carrinho-editar',
  templateUrl: './item-carrinho-editar.component.html',
  styleUrls: ['./item-carrinho-editar.component.css'],
})
export class ItemCarrinhoEditarComponent implements OnInit {
  constructor(
    private dialogRef: MatDialogRef<ItemCarrinhoEditarComponent>,
    @Inject(MAT_DIALOG_DATA) private data: CarrinhoShow
  ) {}

  ngOnInit(): void {}

  fechar() {
    this.dialogRef.close();
  }
}
