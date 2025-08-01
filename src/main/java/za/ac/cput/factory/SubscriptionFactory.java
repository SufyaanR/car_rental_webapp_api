package za.ac.cput.factory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import za.ac.cput.domain.SubscriptionPayment;

public class SubscriptionFactory {

    public SubscriptionPayment newpayment(UUID subscriptionPaymentId, UUID rentalProviderId, BigDecimal amount, LocalDate paymentDate, LocalTime paymentTime ) {
        return new SubscriptionPayment.SubscriptionBuilder()
        .setSubscriptionPaymentId(subscriptionPaymentId)
        .setRentalProviderId(rentalProviderId)
        .setAmount(amount)
        .setPaymentDate(paymentDate)
        .setPaymentTime(paymentTime)
        .build();
    }
    
}
