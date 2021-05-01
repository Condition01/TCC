package br.com.ifeira.auth.dtos;

import br.com.ifeira.auth.enums.Role;
import br.com.ifeira.auth.entity.Pessoa;
import br.com.ifeira.auth.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PessoaDTO {

    private String cpf;
    private String email;
    private String nome;
    private String sobrenome;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String sexo;
    private String telefone;
    private Date dataNasc;
    private Endereco endereco;
    private Role role;

    public PessoaDTO(String email,
                     String nome,
                     String sobrenome,
                     String cpf,
                     String senha,
                     Date dataNasc,
                     Endereco endereco,
                     Role role,
                     String sexo,
                     String telefone) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.role = role;
        this.sexo = sexo;
        this.telefone = telefone;
    }

    public PessoaDTO(String email,
                     String nome,
                     String sobrenome,
                     String cpf,
                     Date dataNasc,
                     Endereco endereco,
                     Role role,
                     String sexo,
                     String telefone) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.role = role;
        this.sexo = sexo;
        this.telefone = telefone;
    }

    public PessoaDTO() { }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public static PessoaDTO fromPessoa(Pessoa pessoa) {
        return new PessoaDTO(
                pessoa.getEmail(),
                pessoa.getNome(),
                pessoa.getSobrenome(),
                pessoa.getCpf(),
                pessoa.getDataNasc(),
                pessoa.getEndereco(),
                pessoa.getRole(),
                pessoa.getSexo(),
                pessoa.getTelefone()
        );
    }
}
