package br.com.ifeira.compra.shared.enums;

public enum StatusPagamento {

    PENDENTE("PENDENTE"),
    CONFIRMADO("CONFIRMADO"),
    CANCELADO("CANCELADO");

    StatusPagamento(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
