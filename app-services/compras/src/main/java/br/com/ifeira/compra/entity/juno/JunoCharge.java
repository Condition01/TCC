package br.com.ifeira.compra.entity.juno;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JunoCharge {

    @JsonProperty("description")
    private String description;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("dueDate")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date dueDate;
    @JsonProperty("installments")
    private Integer installments;
    @JsonProperty("maxOverdueDays")
    private Integer maxOverdueDays;
    @JsonProperty("fine")
    private Integer fine;
    @JsonProperty("interest")
    private Double interest;
    @JsonProperty("discountAmount")
    private Double discountAmount;
    @JsonProperty("discountDays")
    private Integer discountDays;
    @JsonProperty("paymentTypes")
    private List<String> paymentTypes;
    @JsonProperty("paymentAdvance")
    private Boolean paymentAdvance;

    public JunoCharge(String description, Double amount) {
        this.description = description;
        this.amount = amount;
        this.dueDate = new Date();
        this.installments = -1;
        this.maxOverdueDays = 0;
        this.fine = 0;
        this.interest = 0.0;
        this.discountAmount = 0.0;
        this.discountDays = -1;
        this.paymentTypes = new ArrayList<>();
        this.paymentTypes.add("CREDIT_CARD");
        this.paymentAdvance = true;
    }

    public JunoCharge() {
        this.dueDate = new Date();
        this.installments = -1;
        this.maxOverdueDays = 0;
        this.fine = 0;
        this.interest = 0.0;
        this.discountAmount = 0.0;
        this.discountDays = -1;
        this.paymentTypes = new ArrayList<>();
        this.paymentTypes.add("CREDIT_CARD");
        this.paymentAdvance = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Integer getMaxOverdueDays() {
        return maxOverdueDays;
    }

    public void setMaxOverdueDays(Integer maxOverdueDays) {
        this.maxOverdueDays = maxOverdueDays;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getDiscountDays() {
        return discountDays;
    }

    public void setDiscountDays(Integer discountDays) {
        this.discountDays = discountDays;
    }

    public List<String> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<String> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public Boolean getPaymentAdvance() {
        return paymentAdvance;
    }

    public void setPaymentAdvance(Boolean paymentAdvance) {
        this.paymentAdvance = paymentAdvance;
    }
}
