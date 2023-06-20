import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.TotalSum;
import ru.qiwi.payments.service.PaymentServiceImpl;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class PaymentServiceTest {

    final PaymentsDataProvider paymentsDataProvider;
    final PaymentServiceImpl paymentService;

    public PaymentServiceTest() {
        this.paymentsDataProvider = new PaymentsDataProvider();
        paymentsDataProvider.payments = paymentsList();
        this.paymentService = new PaymentServiceImpl(paymentsDataProvider);
    }


    @Test
    public void totalSum() {
        TotalSum totalSum = paymentService.getTotalSum("User1");
        Assertions.assertEquals(totalSum.getAmount(), 280.0);
    }

    @Test
    public void paymentsCount() {
        int paymentsCount1 = paymentService.getPaymentsCount("User3", Payment.Status.OK, Payment.MerchantType.P2P);
        int paymentsCount2 = paymentService.getPaymentsCount("User2", Payment.Status.OK, Payment.MerchantType.SHOP);
        Assertions.assertEquals(paymentsCount1, 4);
        Assertions.assertEquals(paymentsCount2, 1);
    }

    @Test
    public void userReplenishmentsTest() {
        List<Payment> payments = paymentService.getAllReplenishments("User4");
        LocalDateTime firstPaymentDate = payments.get(0).getDate();
        LocalDateTime lastPaymentDate = payments.get(3).getDate();

        Assertions.assertEquals(payments.stream().count(), 6);
        Assertions.assertEquals(firstPaymentDate, LocalDateTime.of(2023, Month.MAY, 01, 19, 31, 33));
        Assertions.assertEquals(lastPaymentDate, LocalDateTime.of(2023, Month.MAY, 29, 19, 24, 40));
    }

    @Test
    public void userPaymentsTest() {
        List<Payment> payments = paymentService.getPayments(
            "User1",
            Payment.MerchantType.P2P,
            LocalDateTime.of(2023, Month.MAY, 01, 00, 00, 00),
            LocalDateTime.of(2023, Month.MAY, 31, 23, 59, 59)
        );
        LocalDateTime firstPaymentDate = payments.get(0).getDate();
        LocalDateTime lastPaymentDate = payments.get(3).getDate();

        Assertions.assertEquals(payments.stream().count(), 4);
        Assertions.assertEquals(firstPaymentDate, LocalDateTime.of(2023, Month.MAY, 06, 19, 31, 40));
        Assertions.assertEquals(lastPaymentDate, LocalDateTime.of(2023, Month.MAY, 29, 19, 35, 40));
    }

    @Test
    public void NRTest() {
        int paymentsCount = paymentService.getNR(
            LocalDateTime.of(2023, Month.MAY, 01, 00, 00, 00),
            LocalDateTime.of(2023, Month.MAY, 31, 23, 59, 59),
            Payment.MerchantType.SHOP
        );
        Assertions.assertEquals(paymentsCount, 30);
    }

    @Test
    public void NDTest() {
        int paymentsCount = paymentService.getND(
            LocalDateTime.of(2023, Month.MAY, 01, 00, 00, 00),
            LocalDateTime.of(2023, Month.MAY, 31, 23, 59, 59),
            Payment.MerchantType.SHOP
        );
        Assertions.assertEquals(paymentsCount, 300);
    }

//    @Test
//    public void top10UsersTest() {
//        // топ 10 пользователей по обороту(списания + пополнения) за период, сортированый список
//    }

    private List<Payment> paymentsList() {
        return Arrays.asList(
            new Payment(
                1,
                LocalDateTime.of(2023, Month.MAY, 29, 10, 30, 40),
                10,
                1,
                "User1",
                "User2",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            ),
            new Payment(
                2,
                LocalDateTime.of(2023, Month.MAY, 29, 19, 31, 40),
                20,
                2,
                "User3",
                "User4",
                Payment.MerchantType.SHOP,
                Payment.Status.IN_PROGRESS
            ),
            new Payment(
                3,
                LocalDateTime.of(2023, Month.MAY, 05, 19, 31, 40),
                30,
                3,
                "User2",
                "User1",
                Payment.MerchantType.P2P,
                Payment.Status.ERROR
            ),
            new Payment(
                4,
                LocalDateTime.of(2023, Month.MAY, 29, 01, 31, 40),
                40,
                4,
                "User4",
                "User3",
                Payment.MerchantType.SHOP,
                Payment.Status.OK
            ),new Payment(
                1,
                LocalDateTime.of(2023, Month.MAY, 29, 19, 35, 40),
                50,
                5,
                "User1",
                "User2",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            ),
            new Payment(
                2,
                LocalDateTime.of(2023, Month.MAY, 29, 19, 40, 40),
                60,
                6,
                "User3",
                "User4",
                Payment.MerchantType.SHOP,
                Payment.Status.OK
            ),new Payment(
                3,
                LocalDateTime.of(2023, Month.MAY, 10, 19, 31, 40),
                70,
                7,
                "User2",
                "User1",
                Payment.MerchantType.SHOP,
                Payment.Status.IN_PROGRESS
            ),
            new Payment(
                4,
                LocalDateTime.of(2023, Month.MAY, 29, 19, 31, 01),
                80,
                8,
                "User4",
                "User3",
                Payment.MerchantType.P2P,
                Payment.Status.ERROR
            ),new Payment(
                1,
                LocalDateTime.of(2023, Month.MAY, 12, 19, 31, 40),
                90,
                9,
                "User1",
                "User2",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            ),
            new Payment(
                2,
                LocalDateTime.of(2023, Month.MAY, 29, 19, 24, 40),
                100,
                10,
                "User3",
                "User4",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            ),new Payment(
                3,
                LocalDateTime.of(2023, Month.MAY, 18, 19, 31, 40),
                110,
                11,
                "User2",
                "User1",
                Payment.MerchantType.SHOP,
                Payment.Status.OK
            ),
            new Payment(
                4,
                LocalDateTime.of(2023, Month.MAY, 01, 19, 31, 40),
                120,
                12,
                "User4",
                "User3",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            ),new Payment(
                1,
                LocalDateTime.of(2023, Month.MAY, 06, 19, 31, 40),
                130,
                13,
                "User1",
                "User2",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            ),
            new Payment(
                2,
                LocalDateTime.of(2023, Month.MAY, 01, 19, 31, 33),
                140,
                14,
                "User3",
                "User4",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            ),
            new Payment(
                3,
                LocalDateTime.of(2023, Month.MAY, 02, 19, 31, 11),
                140,
                14,
                "User3",
                "User4",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            ),
            new Payment(
                4,
                LocalDateTime.of(2023, Month.MAY, 03, 19, 31, 44),
                140,
                14,
                "User3",
                "User4",
                Payment.MerchantType.P2P,
                Payment.Status.OK
            )
        );
    }
}
