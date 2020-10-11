package br.com.ifeira.auth.model;

import javax.persistence.*;

//import org.codehaus.jackson.annotate.JsonProperty;
import br.com.ifeira.auth.model.dtos.UsuarioDTO;
import br.com.ifeira.auth.enums.Roles;
import br.com.ifeira.auth.enums.Situacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
//import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_usuario")
public class Usuario implements UserDetails {

    private static final String ROLE_PREFIX = "ROLE_";

    @Id
    @Column(length = 11)
    private String cpf;

    @Column(length = 50, unique=true)
    private String email;

    private String nome;

    private String sobrenome;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private Date dataNasc;

    private Situacao situcao;

    @Enumerated
    private Roles role;

    public Usuario(String email,
                   String nome,
                   String sobrenome,
                   Date dataNasc,
                   String cpf,
                   String senha,
                   Situacao situacao,
                   Endereco endereco,
                   Roles role) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.situcao = situacao;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.role = role;
    }

    public Usuario(String email,
                   String nome,
                   String sobrenome,
                   String cpf,
                   String senha,
                   Endereco endereco,
                   Date dataNasc,
                   Roles role) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.endereco = endereco;
        this.dataNasc = dataNasc;
        this.role = role;
    }

    public Usuario() { }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        System.out.println(ROLE_PREFIX + role);
        return roles;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
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

    public static Usuario fromDTO(UsuarioDTO usuarioDTO) {
        return new Usuario(
                usuarioDTO.getEmail(),
                usuarioDTO.getNome(),
                usuarioDTO.getSobrenome(),
                usuarioDTO.getCpf(),
                usuarioDTO.getSenha(),
                usuarioDTO.getEndereco(),
                usuarioDTO.getDataNasc(),
                usuarioDTO.getRole()
        );
    }
}
