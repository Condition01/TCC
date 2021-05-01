package br.com.ifeira.auth.entity;

import br.com.ifeira.auth.dtos.PessoaDTO;
import br.com.ifeira.auth.enums.Role;
import br.com.ifeira.auth.enums.Situacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//import org.codehaus.jackson.annotate.JsonProperty;
//import org.springframework.data.annotation.Id;

@Entity
@Table(name = "PESSOA")
public class Pessoa implements UserDetails {

    private static final String ROLE_PREFIX = "ROLE_";

    @Id
    @Column(length = 11, name = "CPF")
    private String cpf;

    @Column(length = 200, name = "NOME")
    private String nome;

    @Column(length = 15, name = "SEXO")
    private String sexo;

    @Column(length = 200, name = "SOBRENOME")
    private String sobrenome;

    @Column(length = 200, unique=true, name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID")
    private Endereco endereco;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Column(name = "DATA_NASC")
    private Date dataNasc;

    @Enumerated(EnumType.STRING)
    @Column(length = 200, name = "SITUACAO")
    private Situacao situcao;

    @Column(length = 15, name = "TELEFONE")
    private String telefone;

    @Enumerated
    private Role role;

    public Pessoa(String email,
                  String nome,
                  String sobrenome,
                  Date dataNasc,
                  String cpf,
                  String senha,
                  Situacao situacao,
                  Endereco endereco,
                  String sexo,
                  String telefone,
                  Role role) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.situcao = situacao;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.role = role;
        this.sexo = sexo;
        this.telefone = telefone;
    }

    public Pessoa(String email,
                  String nome,
                  String sobrenome,
                  String cpf,
                  String senha,
                  Endereco endereco,
                  Date dataNasc,
                  String sexo,
                  String telefone,
                  Role role) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.endereco = endereco;
        this.dataNasc = dataNasc;
        this.role = role;
        this.sexo = sexo;
        this.telefone = telefone;
    }

    public Pessoa() { }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        System.out.println(ROLE_PREFIX + role);
        return roles;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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

    public static Pessoa fromDTO(PessoaDTO pessoaDTO) {
        return new Pessoa(
                pessoaDTO.getEmail(),
                pessoaDTO.getNome(),
                pessoaDTO.getSobrenome(),
                pessoaDTO.getCpf(),
                pessoaDTO.getSenha(),
                pessoaDTO.getEndereco(),
                pessoaDTO.getDataNasc(),
                pessoaDTO.getSexo(),
                pessoaDTO.getTelefone(),
                pessoaDTO.getRole()
        );
    }
}
