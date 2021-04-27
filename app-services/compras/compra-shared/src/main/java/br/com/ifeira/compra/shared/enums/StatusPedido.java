package br.com.ifeira.compra.shared.enums;

public enum StatusPedido {

    PENDENTE("Pendente"),
    CANCELADO("Cancelado"),
    PAGO("Pago"),
    ENTREGUE("Entregue");

    StatusPedido(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
