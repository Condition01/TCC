package br.com.ifeira.compra.shared.enums;

public enum Role {

    CLIENTE("Cliente"),
    FEIRANTE("Feirante"),
    ENTREGADOR("Entregador");

    Role(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
