package br.com.ifeira.pagamento.entity.juno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JunoPaymentReq {

    @JsonProperty("chargeId")
    private String chargeId;
    @JsonProperty("billing")
    private JunoBilling billing;
    @JsonProperty("creditCardDetails")
    private JunoCreditCardDetails creditCardDetails;

    public JunoPaymentReq(String chargeId, JunoBilling billing, JunoCreditCardDetails creditCardDetails) {
        this.chargeId = chargeId;
        this.billing = billing;
        this.creditCardDetails = creditCardDetails;
    }

    public JunoPaymentReq() {
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public JunoBilling getBilling() {
        return billing;
    }

    public void setBilling(JunoBilling billing) {
        this.billing = billing;
    }

    public JunoCreditCardDetails getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setCreditCardDetails(JunoCreditCardDetails creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }

}
