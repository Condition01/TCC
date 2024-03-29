package br.com.ifeira.compra.shared.enums;

public enum Role {

    CLIENTE("0"),
    FEIRANTE("1"),
    ENTREGADOR("2");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Role fromNumber(String value) {
        switch (value) {
            case "0":
                return Role.CLIENTE;
            case "1":
                return Role.FEIRANTE;
            case "2":
                return Role.ENTREGADOR;
        }
        return null;
    }


}
