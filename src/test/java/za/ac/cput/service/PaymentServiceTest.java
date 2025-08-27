package za.ac.cput.service;

import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PaymentServiceTest {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private BusinessUserServiceImpl businessUserService;

    @Autowired
    private BasicUserService basicUserService;

    private BusinessUser businessUser;
    private BasicUser basicUser;
    private Car car;
    private Booking booking;
    private Payment payment;

    @BeforeEach
    void setUp() {
        businessUser = BusinessUserFactory.createBusinessUser(
                "John",
                "Doe",
                LocalDate.of(1985, 1, 1),
                "1234567890123",
                "john@business.com",
                "0821234567",
                UserType.BUSINESS,
                "johndoe",
                "password",
                true,
                "ABSA",
                "John Doe",
                123456,
                "Savings",
                "JD Motors",
                "BR123456"
        );

        businessUser = businessUserService.save(businessUser);

        car = CarFactory.create(
                new byte[10],
                "Toyota",
                "Corolla",
                "Sedan",
                BigDecimal.valueOf(500),
                5,
                450.5f,
                1.6f,
                "Automatic",
                "Reliable car",
                "Johannesburg",
                true,
                businessUser,
                null
        );
        car = carService.save(car);

        basicUser = BasicUserFactory.create(
                "Alice",
                "Smith",
                "alice123",
                "password",
                "alice@example.com",
                "0829876543",
                LocalDate.of(1990, 5, 20),
                "8901234567890",
                true
        );
        basicUser = basicUserService.save(basicUser);

        booking = BookingFactory.create(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 5),
                BigDecimal.valueOf(2000),
                BookingStatus.PENDING,
                basicUser,
                car
        );
        booking = bookingService.save(booking);

        payment = PaymentFactory.create(
                12345678,
                "Alice Smith",
                LocalDate.of(2025, 12, 25),
                "123",
                BigDecimal.valueOf(1000),
                LocalDate.now(),
                LocalTime.now(),
                PaymentStatus.PENDING,
                basicUser,
                booking,
                businessUser,
                null
        );
        payment = paymentService.save(payment);
    }

    @Test
    void testSavePayment() {
        assertNotNull(payment);
        assertNotNull(payment.getPaymentId());
        assertEquals(BigDecimal.valueOf(1000), payment.getAmount());
        assertEquals(basicUser.getUserId(), payment.getUser().getUserId());
        assertEquals(businessUser.getUserId(), payment.getBusinessUser().getUserId());
    }

    @Test
    void testFindById() {
        Payment found = paymentService.findById(payment.getPaymentId());
        assertNotNull(found);
        assertEquals(payment.getPaymentId(), found.getPaymentId());
    }

    @Test
    void testUpdatePayment() {
        Payment updates = new Payment.Builder()
        .setPaymentId(payment.getPaymentId())
        .setAmount(BigDecimal.valueOf(1500))
        .setPaymentStatus(PaymentStatus.SUCCESSFUL)
        .setUser(payment.getUser())
        .setBusinessUser(payment.getBusinessUser())
        .setProUser(payment.getProUser())
        .setBooking(payment.getBooking())
        .build();
        Payment updated = paymentService.update(payment.getPaymentId(), updates);
        assertEquals(BigDecimal.valueOf(1500), updated.getAmount());
        assertEquals(PaymentStatus.SUCCESSFUL, updated.getPaymentStatus());
    }

    @Test
    void testProcessPayment() {
        paymentService.processPayment(payment);
        Payment processed = paymentService.findById(payment.getPaymentId());
        assertEquals(PaymentStatus.SUCCESSFUL, processed.getPaymentStatus());
    }

    @Test
    void testFindAllPayments() {
        List<Payment> payments = paymentService.findAll();
        assertFalse(payments.isEmpty());
        assertTrue(payments.size() >= 1);
    }

    @Test
    void testDeletePayment() {
        paymentService.deleteById(payment.getPaymentId());
        assertThrows(RuntimeException.class, () -> paymentService.findById(payment.getPaymentId()));
    }
}
