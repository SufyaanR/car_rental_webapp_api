package za.ac.cput.service;

import za.ac.cput.domain.ProUser;
import za.ac.cput.factory.ProUserFactory;
import za.ac.cput.service.ProUserServiceImpl;
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
public class ProUserServiceTest {

    @Autowired
    private ProUserServiceImpl proUserService;

    private ProUser proUser;

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
    }

    @Test
    void testSaveProUser() {
        assertNotNull(proUser.getUserId());
        assertEquals("Alice", proUser.getFirstName());
    }

    @Test
    void testFindById() {
        ProUser found = proUserService.findById(proUser.getUserId());
        assertEquals(proUser.getUserId(), found.getUserId());
        assertEquals(proUser.getUsername(), found.getUsername());
    }

    @Test
    void testFindByUsername() {
        Optional<ProUser> found = proUserService.findByUsername(proUser.getUsername());
        assertTrue(found.isPresent());
        assertEquals(proUser.getEmail(), found.get().getEmail());
    }

    @Test
    void testFindAll() {
        List<ProUser> users = proUserService.findAll();
        assertFalse(users.isEmpty());
        assertTrue(users.stream().anyMatch(u -> u.getUserId().equals(proUser.getUserId())));
    }

    @Test
    void testUpdateProUser() {
        ProUser updates = new ProUser.Builder()
                .setFirstName("Alicia")
                .setEmail("alicia.pro@example.com")
                .build();

        ProUser updated = proUserService.update(proUser.getUserId(), updates);

        assertEquals("Alicia", updated.getFirstName());
        assertEquals("alicia.pro@example.com", updated.getEmail());
        assertEquals(proUser.getUsername(), updated.getUsername());
    }

    @Test
    void testLogin() {
        boolean success = proUserService.login(proUser.getUsername(), "password123");
        assertTrue(success);

        boolean fail = proUserService.login(proUser.getUsername(), "wrongPassword");
        assertFalse(fail);
    }

    @Test
    void testDeleteProUser() {
        Long id = proUser.getUserId();
        proUserService.deleteById(id);
        assertThrows(RuntimeException.class, () -> proUserService.findById(id));
    }

    @Test
    void testRegisterInvalidEmail() {
        ProUser invalid = new ProUser.Builder()
                .setUsername("invalidUser")
                .setEmail("invalid-email")
                .setPhoneNumber("0821234567")
                .setPassword("password")
                .setFirstName("Invalid")
                .setLastName("User")
                .build();

        assertThrows(IllegalArgumentException.class, () -> proUserService.register(invalid));
    }

    @Test
    void testRegisterInvalidPhone() {
        ProUser invalid = new ProUser.Builder()
                .setUsername("invalidPhone")
                .setEmail("valid@example.com")
                .setPhoneNumber("1234")
                .setPassword("password")
                .setFirstName("Invalid")
                .setLastName("Phone")
                .build();

        assertThrows(IllegalArgumentException.class, () -> proUserService.register(invalid));
    }
}

