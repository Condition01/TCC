package br.com.ifeira.entrega.enums;

public enum StatusEntrega {

    PENDENTE("PENDENTE"),
    CANCELADO("CANCELADO"),
    ENTREGUE("ENTREGUE"),
    ATRIBUIDA("ATRIBUIDA");

    private String value;

    StatusEntrega(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
