package br.com.ifeira.pagamento.entity.juno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JunoBilling {

    @JsonProperty("email")
    private String email;
    @JsonProperty("address")
    private JunoAddress address;

    public JunoBilling(String email, JunoAddress address) {
        this.email = email;
        this.address = address;
    }

    public JunoBilling() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JunoAddress getAddress() {
        return address;
    }

    public void setAddress(JunoAddress address) {
        this.address = address;
    }

}
