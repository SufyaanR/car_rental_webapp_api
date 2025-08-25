package za.ac.cput.factory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import za.ac.cput.domain.SubscriptionPayment;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.ProUser;

public class SubscriptionFactory {

    public static SubscriptionPayment create(BigDecimal amount,
                                             LocalDate paymentDate,
                                             LocalTime paymentTime,
                                             BusinessUser businessUser,
                                             ProUser proUser) {

        return new SubscriptionPayment.Builder()
                .setAmount(amount)
                .setPaymentDate(paymentDate)
                .setPaymentTime(paymentTime)
                .setProUser(proUser)
                .setBusinessUser(businessUser)
                .build();
    }
}