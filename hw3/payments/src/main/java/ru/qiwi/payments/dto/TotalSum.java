package ru.qiwi.payments.dto;

public class TotalSum {
    final Double amount;
    public String user;

    public TotalSum(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
