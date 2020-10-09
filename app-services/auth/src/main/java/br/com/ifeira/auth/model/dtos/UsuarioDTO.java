package br.com.ifeira.auth.model.dtos;

import br.com.ifeira.auth.model.CustomUser;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO {

    private String email;
    private String nome;
    private String sobrenome;
    private String cpf;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    public UsuarioDTO(String email, String nome, String sobrenome, String cpf, String senha) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public UsuarioDTO(String email, String nome, String sobrenome, String cpf) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    public UsuarioDTO() { }

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

    public static UsuarioDTO fromCustomUser(CustomUser customUser) {
        return new UsuarioDTO(
                customUser.getEmail(),
                customUser.getNome(),
                customUser.getSobrenome(),
                customUser.getCpf()
        );
    }
}
