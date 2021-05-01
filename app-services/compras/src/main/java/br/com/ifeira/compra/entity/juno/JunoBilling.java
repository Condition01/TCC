package br.com.ifeira.compra.entity.juno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JunoBilling {

    @JsonProperty("name")
    private String name;
    @JsonProperty("document")
    private String document;
    @JsonProperty("email")
    private String email;
    @JsonProperty("address")
    private JunoAddress address;

    public JunoBilling(String name, String document, String email, JunoAddress address) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.address = address;
    }

    public JunoBilling() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public JunoAddress getAddress() {
        return address;
    }

    public void setAddress(JunoAddress address) {
        this.address = address;
    }

}
