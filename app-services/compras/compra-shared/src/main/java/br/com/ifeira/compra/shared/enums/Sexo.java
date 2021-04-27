package br.com.ifeira.compra.shared.enums;

public enum Sexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    Sexo(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
