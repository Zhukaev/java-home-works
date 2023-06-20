package ru.qiwi.payments.dto;

public class TotalSum {
    final Double amount;

    public TotalSum(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
