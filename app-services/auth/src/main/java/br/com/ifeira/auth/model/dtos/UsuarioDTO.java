package br.com.ifeira.auth.model.dtos;

import br.com.ifeira.auth.enums.Roles;
import br.com.ifeira.auth.model.Usuario;
import br.com.ifeira.auth.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UsuarioDTO {

    private String cpf;
    private String email;
    private String nome;
    private String sobrenome;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private Date dataNasc;
    private Endereco endereco;
    private Roles role;

    public UsuarioDTO(String email,
                      String nome,
                      String sobrenome,
                      String cpf,
                      String senha,
                      Date dataNasc,
                      Endereco endereco,
                      Roles role) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.role = role;
    }

    public UsuarioDTO(String email,
                      String nome,
                      String sobrenome,
                      String cpf,
                      Date dataNasc,
                      Endereco endereco,
                      Roles role) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.role = role;
    }

    public UsuarioDTO() { }

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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
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

    public static UsuarioDTO fromUsuario(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getDataNasc(),
                usuario.getEndereco(),
                usuario.getRole()
        );
    }
}
