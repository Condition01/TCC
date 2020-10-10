package br.com.ifeira.compras.model;

import br.com.ifeira.compras.enums.Sexo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_cliente")
public class Cliente {

    @Id
    @Column(name = "cpf", length = 11)
    private String cpf;

    private String nome;

    private Date dataNasc;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    private String telefone;

    @OneToOne(mappedBy = "cliente")
    private Pedido pedido;

    public Cliente(String cpf, String nome, Date dataNasc, Sexo sexo, Endereco endereco,
                   String telefone, Pedido pedido) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.pedido = pedido;
    }

    public Cliente() {
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
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
}
