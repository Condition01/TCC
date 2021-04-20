package br.com.Ifeira.compra.entity.shared;

import br.com.Ifeira.compra.enums.shared.StatusPedido;

import java.util.Date;

public class Pedido {
    private Date data;
    private StatusPedido statusPedido;
    private Long numeroPedido;
    private Date dataEntrega;
    private Cupom cupom;
    private Pessoa cliente;
    private Carrinho carrinho;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
