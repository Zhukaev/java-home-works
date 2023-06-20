package ru.qiwi.payments.dataprovider;

import org.springframework.stereotype.Component;
import ru.qiwi.payments.dto.Payment;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class PaymentsDataProvider {
    public List<Payment> payments;
    public List<Payment> getPayments() {
        return payments;
    }
}
