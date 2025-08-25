package za.ac.cput.factory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.domain.ProUser;

public class PaymentFactory {

    public static Payment create(int cardNumber,
            String nameOfCardHolder,
            LocalDate expiryDate,
            String ccv,
            BigDecimal amount,
            LocalDate paymentDate,
            LocalTime paymentTime,
            PaymentStatus paymentStatus,
            BasicUser user,
            Booking booking,
            BusinessUser businessUser,
            ProUser proUser) {

        return new Payment.Builder()
                .setCardNumber(cardNumber)
                .setNameOfCardHolder(nameOfCardHolder)
                .setExpiryDate(expiryDate)
                .setCcv(ccv)
                .setAmount(amount)
                .setPaymentDate(paymentDate)
                .setPaymentTime(paymentTime)
                .setPaymentStatus(paymentStatus)
                .setUser(user)
                .setBooking(booking)
                .setProUser(proUser)
                .setBusinessUser(businessUser)
                .build();
    }
}