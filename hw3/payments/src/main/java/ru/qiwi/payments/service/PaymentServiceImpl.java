package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.TotalSum;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//собственно задание: в PaymentServiceImpl методы с описаниями и тудушками, их нужно реализовать используя stream api
//    для метода getTotal можете сами придумать возвращаемую модельку
@Service
public class PaymentServiceImpl {

    private PaymentsDataProvider paymentsDataProvider;

    public PaymentServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
    }

    // должен вернуть объект TotalSum, который содержит сумму всех списаний
    // и сумму всех пополнений для пользователя с personId
    public TotalSum getTotalSum(String personId) {
        Double sum = paymentsDataProvider.getPayments()
            .stream()
            .filter(payment -> {
                // return String.valueOf(payment.getId()) == personId; - такой вариант почему-то не прокатывает
                return payment.getFromAccount() == personId;
            })
            .mapToDouble(payment -> {
                return payment.getAmount();
            })
            .sum();

        return new TotalSum(sum);
    }

    // должен вернуть количество платежей, совершенных пользователем
    // фильтры на статус платежа и тип мерчанта
    public int getPaymentsCount(String personId, Payment.Status status, Payment.MerchantType merchantType) {
        return (int) paymentsDataProvider.getPayments()
            .stream()
            .filter(payment -> {
                return (payment.getStatus() == status && payment.getMerchantType() == merchantType && payment.getFromAccount() == personId);
            })
            .count();
    }

    // все пополнения пользователя(сортировка по дате)
    public List<Payment> getAllReplenishments(String user) {
        return paymentsDataProvider.getPayments()
            .stream()
            .filter(payment -> {
                return payment.getToAccount() == user;
            })
            .sorted(Comparator.comparing(Payment::getDate))
            .collect(Collectors.toList());
    }

    // все платежи пользователя за период(сортировка по дате)
    public List<Payment> getPayments(
            String personId,
            Payment.MerchantType merchantType,
            LocalDateTime dateFrom,
            LocalDateTime dateTill
    ) {
        return paymentsDataProvider.getPayments()
            .stream()
            .filter(payment -> {
                LocalDateTime date = payment.getDate();
                return payment.getFromAccount() == personId && date.isAfter(dateFrom) && date.isBefore(dateTill) && merchantType == payment.getMerchantType();
            })
            .sorted(Comparator.comparing(Payment::getDate))
            .collect(Collectors.toList());
    }

    // NR(сумма комиссий) за период по мерчанту
    public int getNR(
            LocalDateTime dateFrom,
            LocalDateTime dateTill,
            Payment.MerchantType merchantType
    ) {
        return (int) paymentsDataProvider.getPayments()
            .stream()
            .filter(payment -> {
                return payment.getDate().isAfter(dateFrom) && payment.getDate().isBefore(dateTill) && payment.getMerchantType() == merchantType;
            })
            .mapToDouble(Payment::getCommission)
            .sum();
    }

    // Оборот за период по мерчанту
    public int getND(
            LocalDateTime dateFrom,
            LocalDateTime dateTill,
            Payment.MerchantType merchantType
    ) {
        return (int) paymentsDataProvider.getPayments()
            .stream()
            .filter(payment -> {
                return payment.getDate().isAfter(dateFrom) && payment.getDate().isBefore(dateTill) && payment.getMerchantType() == merchantType;
            })
            .mapToDouble(Payment::getAmount)
            .sum();
    }

    // топ 10 пользователей по обороту(списания + пополнения) за период, сортированый список
//    public List<String> getTopUsers(
//            LocalDateTime dateFrom,
//            LocalDateTime dateTill
//    ) {
//    }
}
