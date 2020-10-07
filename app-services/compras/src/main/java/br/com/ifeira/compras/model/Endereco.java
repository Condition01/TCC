package br.com.ifeira.compras.model;


@Entity
public class Endereco {
    private logradouro String;
    private numero String;
    private cep String;
    private complemento String;
    public logradouro getString() {
        return String;
    }

    public void setString(complemento string) {
        String = string;
    }

    public void setString(cep string) {
        String = string;
    }

    public void setString(numero string) {
        String = string;
    }

    public void setString(logradouro string) {
        String = string;
    }
}
