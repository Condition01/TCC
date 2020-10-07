package br.com.ifeira.compras.model;

import javax.persistence.*;
import br.com.ifeira.compras.model.Endereco

@Entity
public class Cliente {

    @id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cpf;
    private String nome;
    private Date dataNasc;
    private @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @OneToOne
    private Endereco endereco;
    private String telefone;
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
