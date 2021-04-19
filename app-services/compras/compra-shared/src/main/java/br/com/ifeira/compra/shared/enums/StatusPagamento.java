package br.com.ifeira.compra.shared.enums;

public enum StatusPagamento {
    PENDENTE("Pendente"),
    RECUSADO("Recusado"),
    CONFIRMADO("Confirmado");

    private final String value;

    StatusPagamento(String value){
        this.value= value;
    }

    public String getValue() {
        return this.value;
    }
}
