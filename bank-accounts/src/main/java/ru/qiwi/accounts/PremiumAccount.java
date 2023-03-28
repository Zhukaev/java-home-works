package ru.qiwi.accounts;

public final class PremiumAccount extends BankAccount {

    public PremiumAccount(double startAmount) {
        super(startAmount);
    }

    @Override
    protected double getAmountWithCommission(double amount) {
        return amount += 1 ;
    }
}
