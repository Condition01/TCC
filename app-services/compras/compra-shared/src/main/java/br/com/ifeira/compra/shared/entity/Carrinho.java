package br.com.ifeira.compra.shared.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    BigDecimal valorTotal;
    List<ProdutoQuantidade> listaProdutoQuantidade = new ArrayList<ProdutoQuantidade>();
}
