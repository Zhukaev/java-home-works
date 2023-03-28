package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.PaymentWithCommission;

@Service
public class PaymentWithCommissionServiceImpl extends PaymentServiceImpl {

    public PaymentWithCommissionServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        super(paymentsDataProvider);
    }

    @Override
    public int getTotalSum() {
        Payment[] payments = getPayments();
        int totalAmount = 0;

        for (int i = 0; i < payments.length; i ++) {
            PaymentWithCommission payment = (PaymentWithCommission) payments[i];
            int amount = payment.getAmount() + payment.getCommission();
            totalAmount += amount;
        }

        return totalAmount;
    }

    @Override
    protected Payment[] getPayments() {
        return this.paymentsDataProvider.getPaymentWithCommission();
    }
}
