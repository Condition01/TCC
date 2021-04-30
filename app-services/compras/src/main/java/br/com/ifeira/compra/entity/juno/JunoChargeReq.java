package br.com.ifeira.compra.entity.juno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JunoChargeReq {

    @JsonProperty("charge")
    private JunoCharge charge;
    @JsonProperty("billing")
    private JunoBilling billing;

    public JunoChargeReq(JunoCharge charge, JunoBilling billing) {
        this.charge = charge;
        this.billing = billing;
    }

    public JunoChargeReq() {
    }

    public JunoCharge getCharge() {
        return charge;
    }

    public void setCharge(JunoCharge charge) {
        this.charge = charge;
    }

    public JunoBilling getBilling() {
        return billing;
    }

    public void setBilling(JunoBilling billing) {
        this.billing = billing;
    }

}
