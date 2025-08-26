package za.ac.cput.factory;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.ProUser;
import za.ac.cput.domain.UserType;

class ProUserFactoryTest {

    @Test
    void testCreateProUser() {
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String password = "password123";
        String email = "john.doe@example.com";
        String phoneNumber = "1234567890";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String idNumber = "9001011234567";
        boolean login = true;
        String bankName = "FNB";
        String accountHolder = "John Doe";
        int accountNumber = 123456789;
        String accountType = "Savings";

        ProUser proUser = ProUserFactory.create(
                firstName, lastName, username, password, email, phoneNumber,
                dateOfBirth, idNumber, login, bankName, accountHolder,
                accountNumber, accountType);

        assertNotNull(proUser);
        assertEquals(firstName, proUser.getFirstName());
        assertEquals(lastName, proUser.getLastName());
        assertEquals(username, proUser.getUsername());
        assertEquals(password, proUser.getPassword());
        assertEquals(email, proUser.getEmail());
        assertEquals(phoneNumber, proUser.getPhoneNumber());
        assertEquals(dateOfBirth, proUser.getDateOfBirth());
        assertEquals(idNumber, proUser.getIdNumber());
        assertTrue(proUser.isLogin());
        assertEquals(UserType.PRO, proUser.getUserType());
        assertEquals(bankName, proUser.getBankName());
        assertEquals(accountHolder, proUser.getAccountHolder());
        assertEquals(accountNumber, proUser.getAccountNumber());
        assertEquals(accountType, proUser.getAccountType());

        System.out.println(proUser);
    }
}
