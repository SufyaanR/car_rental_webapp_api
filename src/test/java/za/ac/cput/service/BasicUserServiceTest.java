package za.ac.cput.service;

import za.ac.cput.domain.BasicUser;
import za.ac.cput.service.BasicUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BasicUserServiceTest {

    @Autowired
    private BasicUserService basicUserService;

    private BasicUser user;

    @BeforeEach
    void setUp() {
        user = new BasicUser.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com")
                .setPhoneNumber("+27831234567")
                .setUsername("johndoe")
                .setPassword("password")
                .build();
    }

    @Test
    void testSave() {
        BasicUser saved = basicUserService.save(user);
        assertNotNull(saved.getUserId());
        assertEquals("john.doe@example.com", saved.getEmail());
    }

    @Test
    void testRegister_ValidUser() {
        BasicUser registered = basicUserService.register(user);
        assertNotNull(registered.getUserId());
    }

    @Test
    void testRegister_InvalidEmail() {
        user = new BasicUser.Builder()
                .setEmail("invalid-email")
                .setPhoneNumber("+27831234567")
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> basicUserService.register(user));
        assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    void testLogin() {
        basicUserService.save(user);
        assertTrue(basicUserService.login("johndoe", "password"));
        assertFalse(basicUserService.login("johndoe", "wrongpass"));
        assertFalse(basicUserService.login("unknown", "password"));
    }

    @Test
    void testFindByUsername() {
        basicUserService.save(user);
        Optional<BasicUser> found = basicUserService.findByUsername("johndoe");
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getFirstName());
    }

    @Test
    void testFindById() {
        BasicUser saved = basicUserService.save(user);
        BasicUser found = basicUserService.findById(saved.getUserId());
        assertEquals("John", found.getFirstName());
    }

    @Test
    void testFindAll() {
        basicUserService.save(user);
        List<BasicUser> users = basicUserService.findAll();
        assertTrue(users.size() > 0);
    }

    @Test
    void testUpdate() {
        BasicUser saved = basicUserService.save(user);

        BasicUser updates = new BasicUser.Builder()
                .setFirstName("Jane")
                .build();

        BasicUser updated = basicUserService.update(saved.getUserId(), updates);
        assertEquals("Jane", updated.getFirstName());
        assertEquals("Doe", updated.getLastName());
    }

    @Test
    void testDeleteById() {
        BasicUser saved = basicUserService.save(user);
        basicUserService.deleteById(saved.getUserId());
        assertThrows(RuntimeException.class,
                () -> basicUserService.findById(saved.getUserId()));
    }
}

