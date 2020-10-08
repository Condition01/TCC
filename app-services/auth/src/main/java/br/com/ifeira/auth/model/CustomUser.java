package br.com.ifeira.auth.model;

import javax.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
public class CustomUser implements UserDetails {

    @Id
    private String id;
    private String email;
    private String nome;
    private String cpf;
    private String senha;
    private Situacao situcao;
    private Collection<? extends GrantedAuthority> roles;

    public CustomUser(String email,
                      String nome,
                      String cpf,
                      String senha,
                      Situacao situacao,
                      Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.situcao = situacao;
        this.roles = this.getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Situacao getSitucao() {
        return situcao;
    }

    public void setSitucao(Situacao situcao) {
        this.situcao = situcao;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.situcao != Situacao.EXPIRADO;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.situcao != Situacao.BLOQUEADO;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.situcao == Situacao.HABILITADO;
    }
}
