package br.com.ifeira.compra.shared.enums;

public enum StatusPagamento {

    PENDENTE("Pendente"),
    CONFIRMADO("Confirmado"),
    RECUSADO("Recusado"),
    CANCELADO("Cancelado");

    StatusPagamento(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
