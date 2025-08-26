package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.domain.ProUser;
import za.ac.cput.domain.UserType;

public class PaymentFactoryTest {

    @Test
    void testCreatePaymentForBusinessUser() {
        BasicUser user = new BasicUser.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setUsername("johndoe")
                .setPassword("password")
                .setEmail("john@example.com")
                .setPhoneNumber("1234567890")
                .setDateOfBirth(LocalDate.of(1990, 1, 1))
                .setIdNumber("9001011234567")
                .setLogin(true)
                .setUserType(UserType.BASIC)
                .build();

        BusinessUser businessUser = new BusinessUser.Builder()
                .setFirstName("Alice")
                .setLastName("Smith")
                .setDateOfBirth(LocalDate.of(1985, 5, 15))
                .setIdNumber("8505159876543")
                .setEmail("alice@business.com")
                .setPhoneNumber("0987654321")
                .setUserType(UserType.BUSINESS)
                .setUsername("alicebusiness")
                .setPassword("secure")
                .setLogin(true)
                .setBankName("Bank")
                .setAccountHolder("Alice Smith")
                .setAccountNumber(123456)
                .setAccountType("Checking")
                .setBusinessName("Alice Corp")
                .setBusinessRegistrationNumber("REG123456")
                .build();

        Booking booking = new Booking.Builder()
                .setStartDate(LocalDate.of(2025, 8, 25))
                .setEndDate(LocalDate.of(2025, 8, 30))
                .setTotalPrice(BigDecimal.valueOf(1000))
                .setBookingStatus(za.ac.cput.domain.BookingStatus.CONFIRMED)
                .setUser(user)
                .setCar(null) 
                .build();

        Payment payment = PaymentFactory.create(
                12345678,
                "Alice Smith",
                LocalDate.of(2026, 12, 31),
                "123",
                BigDecimal.valueOf(500),
                LocalDate.of(2025, 8, 25),
                LocalTime.of(14, 30),
                PaymentStatus.SUCCESSFUL,
                user,
                booking,
                businessUser,
                null 
        );

        assertNotNull(payment);
        assertEquals(businessUser, payment.getBusinessUser());
        assertNull(payment.getProUser());
        assertEquals(BigDecimal.valueOf(500), payment.getAmount());
        assertEquals(PaymentStatus.SUCCESSFUL, payment.getPaymentStatus());

        System.out.println(payment);
    }

    @Test
    void testCreatePaymentForProUser() {
        BasicUser user = new BasicUser.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setUsername("johndoe")
                .setPassword("password")
                .setEmail("john@example.com")
                .setPhoneNumber("1234567890")
                .setDateOfBirth(LocalDate.of(1990, 1, 1))
                .setIdNumber("9001011234567")
                .setLogin(true)
                .setUserType(UserType.BASIC)
                .build();

        ProUser proUser = new ProUser.Builder()
                .setFirstName("Bob")
                .setLastName("Johnson")
                .setDateOfBirth(LocalDate.of(1980, 7, 20))
                .setIdNumber("8007206543210")
                .setEmail("bob@pro.com")
                .setPhoneNumber("1122334455")
                .setUserType(UserType.PRO)
                .setUsername("bobjohnson")
                .setPassword("secure")
                .setLogin(true)
                .setBankName("Bank")
                .setAccountHolder("Bob Johnson")
                .setAccountNumber(654321)
                .setAccountType("Savings")
                .build();

        Booking booking = new Booking.Builder()
                .setStartDate(LocalDate.of(2025, 8, 25))
                .setEndDate(LocalDate.of(2025, 8, 30))
                .setTotalPrice(BigDecimal.valueOf(1200))
                .setBookingStatus(za.ac.cput.domain.BookingStatus.CONFIRMED)
                .setUser(user)
                .setCar(null) 
                .build();

        Payment payment = PaymentFactory.create(
                87654321,
                "Bob Johnson",
                LocalDate.of(2026, 11, 30),
                "321",
                BigDecimal.valueOf(750),
                LocalDate.of(2025, 8, 25),
                LocalTime.of(15, 0),
                PaymentStatus.PENDING,
                user,
                booking,
                null,  
                proUser
        );

        assertNotNull(payment);
        assertEquals(proUser, payment.getProUser());
        assertNull(payment.getBusinessUser());
        assertEquals(BigDecimal.valueOf(750), payment.getAmount());
        assertEquals(PaymentStatus.PENDING, payment.getPaymentStatus());

        System.out.println(payment);
    }
}