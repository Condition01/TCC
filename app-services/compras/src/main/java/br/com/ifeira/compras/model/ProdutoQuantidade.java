package br.com.ifeira.compras.model;

import rx.BackpressureOverflow;

import javax.persistence.*;

@Entity
public class ProdutoQuantidade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    private Produto produto;
    int quantidade;
}
