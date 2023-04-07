package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;

@Service
public class PaymentServiceImpl extends AbstractPaymentService<Payment> {

    protected PaymentsDataProvider paymentsDataProvider;

    public PaymentServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
        this.payments = paymentsDataProvider::getPayments;
        this.commission = (Payment) -> 0;
    }
}
