package za.ac.cput.service;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.UserType;
import za.ac.cput.factory.BusinessUserFactory;
import za.ac.cput.service.BusinessUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BusinessUserServiceTest {

    @Autowired
    private BusinessUserServiceImpl businessUserService;

    private BusinessUser businessUser;

    @BeforeEach
    void setup() {
        businessUser = BusinessUserFactory.createBusinessUser(
                "John", "Doe",
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
    }

    @Test
    void testSaveBusinessUser() {
        assertNotNull(businessUser.getUserId());
    }

    @Test
    void testFindById() {
        BusinessUser found = businessUserService.findById(businessUser.getUserId());
        assertEquals(businessUser.getUserId(), found.getUserId());
    }

    @Test
    void testFindAll() {
        List<BusinessUser> users = businessUserService.findAll();
        assertTrue(users.size() >= 1);
    }

    @Test
    void testUpdateBusinessUser() {
        BusinessUser updates = new BusinessUser.Builder()
                .setFirstName("Jane")
                .setLastName("Smith")
                .build();

        BusinessUser updated = businessUserService.update(businessUser.getUserId(), updates);

        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals(businessUser.getEmail(), updated.getEmail()); 
        assertEquals(businessUser.getUsername(), updated.getUsername()); 
    }

    @Test
    void testDeleteBusinessUser() {
        Long id = businessUser.getUserId();
        businessUserService.deleteById(id);
        assertThrows(RuntimeException.class, () -> businessUserService.findById(id));
    }

    @Test
    void testRegisterWithValidData() {
        BusinessUser newUser = BusinessUserFactory.createBusinessUser(
                "Alice", "Johnson",
                LocalDate.of(1990, 5, 20),
                "8901234567890",
                "alice@example.com",
                "0829876543",
                UserType.BUSINESS,
                "alice123",
                "password",
                true,
                "FNB",
                "Alice Johnson",
                654321,
                "Current",
                "AJ Services",
                "BR654321"
        );

        BusinessUser registered = businessUserService.register(newUser);
        assertNotNull(registered.getUserId());
    }

    @Test
    void testRegisterWithInvalidEmail() {
        BusinessUser invalidUser = new BusinessUser.Builder()
                .setFirstName("Bob")
                .setLastName("Brown")
                .setEmail("invalid-email")
                .setPhoneNumber("0821234567")
                .setUsername("bobbrown")
                .setPassword("pass")
                .setUserType(UserType.BUSINESS)
                .build();

        assertThrows(IllegalArgumentException.class, () -> businessUserService.register(invalidUser));
    }

    @Test
    void testLoginSuccess() {
        assertTrue(businessUserService.login("johndoe", "password"));
    }

    @Test
    void testLoginFailure() {
        assertFalse(businessUserService.login("johndoe", "wrongpassword"));
        assertFalse(businessUserService.login("unknown", "password"));
    }

    @Test
    void testFindByUsername() {
        Optional<BusinessUser> found = businessUserService.findByUsername("johndoe");
        assertTrue(found.isPresent());
        assertEquals(businessUser.getUserId(), found.get().getUserId());
    }
}
