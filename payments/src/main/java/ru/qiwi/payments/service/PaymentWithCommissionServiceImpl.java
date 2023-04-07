package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.PaymentWithCommission;

@Service
public class PaymentWithCommissionServiceImpl extends AbstractPaymentService<PaymentWithCommission> {

    protected PaymentsDataProvider paymentsDataProvider;

    public PaymentWithCommissionServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
        this.payments = paymentsDataProvider::getPaymentWithCommission;
        this.commission = PaymentWithCommission::getCommission;
    }
}
