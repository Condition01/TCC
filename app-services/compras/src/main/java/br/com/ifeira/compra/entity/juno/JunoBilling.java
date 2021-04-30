package br.com.ifeira.compra.entity.juno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JunoBilling {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("document")
    private String document;
    @JsonProperty("address")
    private JunoAddress address;

    public JunoBilling(String name, String email, String document, JunoAddress address) {
        this.name = name;
        this.email = email;
        this.document = document;
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
