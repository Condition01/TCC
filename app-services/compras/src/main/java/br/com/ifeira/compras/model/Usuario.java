package br.com.ifeira.compras.model;

import br.com.ifeira.compras.enums.Roles;
import br.com.ifeira.compras.enums.Sexo;

import javax.persistence.*;

@Entity
@Table(name = "tbl_usuario")
public class Usuario {
    @Id
    @Column(name = "cpf", length = 11)
    private String cpf;
    private String nome;
    private Sexo sexo;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    private String telefone;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToOne(mappedBy = "cliente")
    private Pedido pedido_cliente;

    @OneToOne(mappedBy = "feirante")
    private Pedido pedido_feirante;

    @OneToOne(mappedBy = "entregador")
    private Entrega entrega;

    public Usuario(String cpf,
                   String nome,
                   Sexo sexo,
                   Endereco endereco,
                   String telefone,
                   Roles role,
                   Pedido pedido_cliente,
                   Pedido pedido_feirante,
                   Entrega entrega) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.role = role;
        this.pedido_cliente = pedido_cliente;
        this.pedido_feirante = pedido_feirante;
        this.entrega = entrega;
    }

    public Usuario() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Pedido getPedido_cliente() {
        return pedido_cliente;
    }

    public void setPedido_cliente(Pedido pedido_cliente) {
        this.pedido_cliente = pedido_cliente;
    }

    public Pedido getPedido_feirante() {
        return pedido_feirante;
    }

    public void setPedido_feirante(Pedido pedido_feirante) {
        this.pedido_feirante = pedido_feirante;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
}
