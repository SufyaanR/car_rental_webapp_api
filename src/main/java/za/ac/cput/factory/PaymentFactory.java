package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class PaymentFactory {

    public Payment newPayment(UUID paymentId, int cardNumber, String nameOfCardHolder, LocalDate expiryDate, String cvv, BigDecimal amount, LocalDate paymentDate, LocalTime paymentTime, UUID userId, UUID bookingId, UUID rentalProviderId) {
        return new Payment.PaymentBuilder()
                .setPaymentId(paymentId)
                .setCardNumber(cardNumber)
                .setNameOfCardHolder(nameOfCardHolder)
                .setExpiryDate(expiryDate)
                .setCvv(cvv)
                .setPaymentDate(paymentDate)
                .setAmount(amount)
                .setPaymentTime(paymentTime)
                .setUserId(userId)
                .setBookingId(bookingId)
                .setRentalProviderId(rentalProviderId)
                .build();
    }

}