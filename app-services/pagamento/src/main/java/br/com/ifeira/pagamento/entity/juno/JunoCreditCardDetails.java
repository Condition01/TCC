package br.com.ifeira.pagamento.entity.juno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JunoCreditCardDetails {

    @JsonProperty("creditCardId")
    private String credCardId;

    public JunoCreditCardDetails(String credCardId) {
        this.credCardId = credCardId;
    }

    public JunoCreditCardDetails() {
    }

    public String getCredCardId() {
        return credCardId;
    }

    public void setCredCardId(String credCardId) {
        this.credCardId = credCardId;
    }

}
