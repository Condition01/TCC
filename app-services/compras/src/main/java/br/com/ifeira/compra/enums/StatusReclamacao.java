package br.com.ifeira.compra.enums;

public enum StatusReclamacao {

    PENDENTE("Pendente"),
    RESPONDIDO("Respondido"),
    RESOLVIDO("Resolvido");

    private String value;

    StatusReclamacao(String value) {
        this.value= value;
    }

    public String getValue() {
        return value;
    }

}
