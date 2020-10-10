package br.com.ifeira.compras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entregador {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cpf;
    private String nome;
    private String telefone;
    private boolean situacao;

    public Entregador(String cpf, String nome, String telefone, boolean situacao) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.situacao = situacao;
    }

    public Entregador() {
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
