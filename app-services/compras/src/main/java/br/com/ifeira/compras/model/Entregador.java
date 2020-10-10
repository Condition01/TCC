package br.com.ifeira.compras.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_entregador")
public class Entregador {
    @Id
    @Column(name = "cpf",length = 11)
    private String cpf;

    private String nome;

    private String telefone;

    private boolean situacao;

    @OneToOne(mappedBy = "entregador")
    private Pedido pedido;

    public Entregador(String cpf,
                      String nome,
                      String telefone,
                      boolean situacao,
                      Pedido pedido) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.situacao = situacao;
        this.pedido = pedido;
    }

    public Entregador() { }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
