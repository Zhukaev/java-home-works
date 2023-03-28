package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

    protected PaymentsDataProvider paymentsDataProvider;

    public PaymentServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
    }

    @Override
    public int getTotalSum() {
        Payment[] payments = getPayments();
        int totalAmount = 0;

        for (int i = 0; i < payments.length; i ++) {
           totalAmount += payments[i].getAmount();
        }

        return totalAmount;
    }

    @Override
    public int getPaymentsCount() {
        return getPayments().length;
    }

    protected Payment[] getPayments() {
        return paymentsDataProvider.getPayments();
    }
}
