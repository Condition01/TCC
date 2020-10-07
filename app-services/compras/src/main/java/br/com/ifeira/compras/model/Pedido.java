package br.com.ifeira.compras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {
    @id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private Date data;
    @OneToOne
    private Cliente cliente;
    @Enumeted(EnumType.String)
    private StatusPedido statusPedido;
    @OneToOne
    private Entregador entregador;
    @OneToOne
    private Pagamento pagamento;
    private List<Produto> listaProdutos = new ArrayList<Produto>();

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
