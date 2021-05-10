package br.com.ifeira.entrega.entity;

import br.com.ifeira.compra.shared.entity.Endereco;
import br.com.ifeira.compra.shared.entity.Feira;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.compra.shared.enums.Role;
import br.com.ifeira.compra.shared.enums.Sexo;

import java.util.Date;
import java.util.List;

public class Entregador extends Pessoa {

    private List<Feira> feiras;

    public Entregador(String nome, String sobrenome, String cpf, Date dataNascimento, Sexo sexo, Endereco endereco, String telefone, Role role, String situacao, String email, List<Feira> feiras) {
        super(nome, sobrenome, cpf, dataNascimento, sexo, endereco, telefone, role, situacao, email);
        this.feiras = feiras;
    }

    public Entregador() {
        super();
    }

    public List<Feira> getFeiras() {
        return feiras;
    }

    public void setFeiras(List<Feira> feiras) {
        this.feiras = feiras;
    }

}
