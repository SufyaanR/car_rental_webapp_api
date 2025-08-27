package za.ac.cput.factory;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.UserType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class BusinessUserFactoryTest {

    @Test
    void testCreateBusinessUser() {
        BusinessUser businessUser = BusinessUserFactory.createBusinessUser(
                "John",
                "Doe",
                LocalDate.of(1990, 5, 15),
                "9005151234567",
                "john.doe@example.com",
                "0812345678",
                UserType.BUSINESS,
                "johndoe",
                "password123",
                true,
                "Standard Bank",
                "John Doe",
                123456789,
                "Savings",
                "Doe Enterprises",
                "BRN123456"
        );

        assertNotNull(businessUser);
        assertEquals("John", businessUser.getFirstName());
        assertEquals("Doe Enterprises", businessUser.getBusinessName());
        assertEquals(UserType.BUSINESS, businessUser.getUserType());
        assertEquals("BRN123456", businessUser.getBusinessRegistrationNumber());

        assertNotNull(businessUser.getCars());
        assertTrue(businessUser.getCars().isEmpty());
        assertNotNull(businessUser.getSubscriptionPayments());
        assertTrue(businessUser.getSubscriptionPayments().isEmpty());

        System.out.println(businessUser);

    }
    
}
