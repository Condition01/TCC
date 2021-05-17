package br.com.ifeira.entrega.enums;

public enum StatusEntrega {

    REALIZADA("REALIZADA"),
    ATRIBUIDA("ATRIBUIDA"),
    CANCELADA("CANCELADA");

    private String value;

    StatusEntrega(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
