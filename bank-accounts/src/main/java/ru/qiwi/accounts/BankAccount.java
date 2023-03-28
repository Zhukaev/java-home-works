package ru.qiwi.accounts;

public abstract class BankAccount {
    private double amount;

    public BankAccount(double startAmount) {
        this.amount = startAmount;
    }

    protected abstract double getAmountWithCommission(double amount);

    public double getAmount() {
        return amount;
    }

    public void getMoney(double amount) {
        double sumToTakeOff = getAmountWithCommission(amount);
        if (this.amount >= sumToTakeOff) {
            double d = this.amount - sumToTakeOff;
            this.amount = d;
        }
    }
}
