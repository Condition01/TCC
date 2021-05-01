package br.com.ifeira.auth.enums;

public enum Situacao {
    BLOQUEADO("BLOQUEADO"),
    EXPIRADO("EXPIRADO"),
    HABILITADO("HABILITADO");

    private String value;

    Situacao(String value) {
        this.value = value;
    }
}
