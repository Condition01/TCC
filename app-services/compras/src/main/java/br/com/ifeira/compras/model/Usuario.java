package br.com.ifeira.compras.model;

import br.com.ifeira.compras.enums.Roles;
import br.com.ifeira.compras.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "tbl_usuario")
public class Usuario {
    @Id
    @Column(name = "cpf", length = 11)
    private String cpf;
    private String nome;
    private String sobrenome;
    private Sexo sexo;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Endereco endereco;

    private String telefone;

    @Enumerated
    private Roles role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();


    public Usuario(String cpf,
                   String nome,
                   String sobrenome,
                   Sexo sexo,
                   Endereco endereco,
                   String telefone,
                   Roles role,
                   List<Pedido> pedidos) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.role = role;
        this.pedidos = pedidos;
    }

    public Usuario() {
    }

    public void adicionarPedidos(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void removerPedidos(Long numero) throws Exception {
        Optional<Pedido> optPedido = this.pedidos.stream()
                .parallel()
                .filter(pedido1 -> pedido1.getNumero() == numero)
                .findFirst();
        if (optPedido.isPresent()) {
            Pedido pedidoRemovido = optPedido.get();
            this.pedidos.remove(pedidoRemovido);
        } else {
            throw new Exception("Pedido não encontrado para remoção");
        }
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
