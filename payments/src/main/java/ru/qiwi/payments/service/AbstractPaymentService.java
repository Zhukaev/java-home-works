package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dto.Payment;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
public abstract class AbstractPaymentService<T extends Payment> implements PaymentService {

    protected Supplier<T[]> payments;
    protected Function<T, Integer> commission;
    @Override
    public int getTotalSum() {
        return getSum(payments.get());
    }

    @Override
    public int getPaymentsCount() {
        return payments.get().length;
    }

    protected int getSum(T[] payments) {
        int totalAmount = 0;

        for (T payment : payments) {
            totalAmount += payment.getAmount() + commission.apply(payment);
        }
        return totalAmount;
    }
}

