package ru.qiwi.accounts;

public final class SimpleAccount extends BankAccount {

    public SimpleAccount(double startAmount) {
        super(startAmount);
    }

    @Override
    protected double getAmountWithCommission(double amount) {
        return (amount * 0.05) + amount;
    }
}
