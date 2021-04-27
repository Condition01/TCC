package br.com.ifeira.compra.shared.entity;

import br.com.ifeira.compra.shared.enums.Role;
import br.com.ifeira.compra.shared.enums.Sexo;

import java.util.Date;

public class Pessoa {

    private String nome;
    private String cpf;
    private Date dataNascimento;
    private Sexo sexo;
    private Endereco endereco;
    private String telefone;
    private Role role;

    public Pessoa(String nome, String cpf, Date dataNascimento, Sexo sexo, Endereco endereco, String telefone, Role role) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.role = role;
    }

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
