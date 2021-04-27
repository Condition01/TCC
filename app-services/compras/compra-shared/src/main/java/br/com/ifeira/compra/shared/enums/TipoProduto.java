package br.com.ifeira.compra.shared.enums;

public enum TipoProduto {

    FRUTA("Fruta"),
    VERDURA("Verdura"),
    LEGUMES("Legumes");

    TipoProduto(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
