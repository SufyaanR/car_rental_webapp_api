package za.ac.cput.service;

import za.ac.cput.domain.*;
import za.ac.cput.factory.BusinessUserFactory;
import za.ac.cput.factory.ProUserFactory;
import za.ac.cput.factory.SubscriptionFactory;
import za.ac.cput.service.BusinessUserServiceImpl;
import za.ac.cput.service.ProUserServiceImpl;
import za.ac.cput.service.SubscriptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SubscriptionPaymentServiceTest {

    @Autowired
    private SubscriptionServiceImpl subscriptionPaymentService;

    @Autowired
    private ProUserServiceImpl proUserService;

    @Autowired
    private BusinessUserServiceImpl businessUserService;

    private ProUser proUser;
    private BusinessUser businessUser;
    private SubscriptionPayment subscriptionPayment;

    @BeforeEach
    void setup() {
        proUser = ProUserFactory.create(
                "Alice",
                "Johnson",
                "alicePro",
                "password123",
                "alice.pro@example.com",
                "0829876543",
                LocalDate.of(1990, 5, 20),
                "9005201234567",
                true,
                "FNB",
                "Alice Johnson",
                987654,
                "Savings");
        proUser = proUserService.save(proUser);

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
                "BR123456");
                
        businessUser = businessUserService.save(businessUser);

        subscriptionPayment = SubscriptionFactory.create(
                BigDecimal.valueOf(500),
                LocalDate.now(),
                LocalTime.now(),
                null,
                proUser);
        subscriptionPayment = subscriptionPaymentService.save(subscriptionPayment);
    }

    @Test
    void testSaveAndFindById() {
        assertNotNull(subscriptionPayment.getSubscriptionPaymentId());

        SubscriptionPayment found = subscriptionPaymentService.findById(subscriptionPayment.getSubscriptionPaymentId());
        assertEquals(subscriptionPayment.getSubscriptionPaymentId(), found.getSubscriptionPaymentId());
        assertEquals(proUser, found.getProUser());
        assertNull(found.getBusinessUser());
    }

    @Test
    void testFindAll() {
        List<SubscriptionPayment> allPayments = subscriptionPaymentService.findAll();
        assertTrue(allPayments.size() >= 1);
    }

    @Test
    void testUpdateSubscriptionPayment() {
        SubscriptionPayment updates = new SubscriptionPayment.Builder()
                .setAmount(BigDecimal.valueOf(600))
                .setProUser(proUser)
                .build();

        SubscriptionPayment updated = subscriptionPaymentService.update(subscriptionPayment.getSubscriptionPaymentId(),
                updates);
        assertEquals(BigDecimal.valueOf(600), updated.getAmount());
        assertEquals(proUser, updated.getProUser());
        assertNull(updated.getBusinessUser());
    }

    @Test
    void testCreateForBusinessUser() {
        SubscriptionPayment payment = SubscriptionFactory.create(
                BigDecimal.valueOf(750),
                LocalDate.now(),
                LocalTime.now(),
                businessUser,
                null);
        SubscriptionPayment saved = subscriptionPaymentService.createForBusinessUser(businessUser, payment);
        assertNotNull(saved.getSubscriptionPaymentId());
        assertEquals(businessUser, saved.getBusinessUser());
        assertNull(saved.getProUser());
    }

    @Test
    void testDeleteSubscriptionPayment() {
        Long id = subscriptionPayment.getSubscriptionPaymentId();
        subscriptionPaymentService.deleteById(id);
        assertThrows(RuntimeException.class, () -> subscriptionPaymentService.findById(id));
    }
}
