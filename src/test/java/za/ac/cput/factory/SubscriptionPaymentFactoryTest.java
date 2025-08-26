package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.SubscriptionPayment;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.ProUser;

class SubscriptionPaymentFactoryTest {

    @Test
    void testCreateSubscriptionPaymentWithBusinessUser() {
        BigDecimal amount = new BigDecimal("499.99");
        LocalDate paymentDate = LocalDate.of(2025, 8, 21);
        LocalTime paymentTime = LocalTime.of(14, 30);

        BusinessUser businessUser = new BusinessUser.Builder()
                .setFirstName("Alice")
                .setLastName("Smith")
                .setDateOfBirth(LocalDate.of(1985, 5, 15))
                .setIdNumber("8505151234567")
                .setEmail("alice@example.com")
                .setPhoneNumber("0123456789")
                .setUserType(null)
                .setUsername("alice123")
                .setPassword("pass123")
                .setLogin(true)
                .setBankName("Standard Bank")
                .setAccountHolder("Alice Smith")
                .setAccountNumber(123456)
                .setAccountType("Savings")
                .setBusinessName("Alice Rentals")
                .setBusinessRegistrationNumber("BRN123456")
                .build();

        SubscriptionPayment subscriptionPayment = SubscriptionFactory.create(
                amount, paymentDate, paymentTime, businessUser, null);

        assertNotNull(subscriptionPayment);
        assertEquals(amount, subscriptionPayment.getAmount());
        assertEquals(paymentDate, subscriptionPayment.getPaymentDate());
        assertEquals(paymentTime, subscriptionPayment.getPaymentTime());
        assertEquals(businessUser, subscriptionPayment.getBusinessUser());
        assertNull(subscriptionPayment.getProUser());

        System.out.println(subscriptionPayment);
    }

    @Test
    void testCreateSubscriptionPaymentWithProUser() {
        BigDecimal amount = new BigDecimal("299.99");
        LocalDate paymentDate = LocalDate.of(2025, 8, 21);
        LocalTime paymentTime = LocalTime.of(10, 15);

        ProUser proUser = new ProUser.Builder()
                .setFirstName("Bob")
                .setLastName("Jones")
                .setUsername("bobj")
                .setPassword("pass456")
                .setEmail("bob@example.com")
                .setPhoneNumber("0987654321")
                .setDateOfBirth(LocalDate.of(1990, 2, 20))
                .setIdNumber("9002201234567")
                .setLogin(true)
                .setUserType(null)
                .setBankName("FNB")
                .setAccountHolder("Bob Jones")
                .setAccountNumber(654321)
                .setAccountType("Current")
                .build();

        SubscriptionPayment subscriptionPayment = SubscriptionFactory.create(
                amount, paymentDate, paymentTime, null, proUser);

        assertNotNull(subscriptionPayment);
        assertEquals(amount, subscriptionPayment.getAmount());
        assertEquals(paymentDate, subscriptionPayment.getPaymentDate());
        assertEquals(paymentTime, subscriptionPayment.getPaymentTime());
        assertEquals(proUser, subscriptionPayment.getProUser());
        assertNull(subscriptionPayment.getBusinessUser());

        System.out.println(subscriptionPayment);
    }
}
