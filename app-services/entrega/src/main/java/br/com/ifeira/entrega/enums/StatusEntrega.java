package br.com.ifeira.entrega.enums;

public enum StatusEntrega {

    PENDENTE("PENDENTE"),
    CANCELADA("CANCELADA"),
    REALIZADA("REALIZADA"),
    ATRIBUIDA("ATRIBUIDA");

    private String value;

    StatusEntrega(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
