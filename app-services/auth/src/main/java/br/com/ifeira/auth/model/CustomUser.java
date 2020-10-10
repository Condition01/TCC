package br.com.ifeira.auth.model;

import javax.persistence.*;

//import org.codehaus.jackson.annotate.JsonProperty;
import br.com.ifeira.auth.model.dtos.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
//import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_usuario")
public class CustomUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique=true)
    private String email;
    private String nome;
    private String sobrenome;
    @Column(unique=true)
    private String cpf;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private Situacao situcao;

    @ElementCollection
    private List<Roles> roles = new ArrayList<>();

    public CustomUser(String email,
                      String nome,
                      String sobrenome,
                      String cpf,
                      String senha,
                      Situacao situacao) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.situcao = situacao;
    }

    public CustomUser(String email,
                      String nome,
                      String sobrenome,
                      String cpf,
                      String senha) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public CustomUser() { }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setSitucao(Situacao situcao) {
        this.situcao = situcao;
    }

    public void addRoles(Roles role) {
        this.roles.add(role);
    }

    public void removeRoles(Roles role) {
        this.roles.remove(role);
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.situcao == Situacao.HABILITADO;
    }

    public static CustomUser fromDTO(UsuarioDTO usuarioDTO) {
        return new CustomUser(
                usuarioDTO.getEmail(),
                usuarioDTO.getNome(),
                usuarioDTO.getSobrenome(),
                usuarioDTO.getCpf(),
                usuarioDTO.getSenha());
    }
}
