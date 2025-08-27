package za.ac.cput.factory;

import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.UserType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BasicUserFactoryTest {

    @Test
    void testCreateBasicUser() {
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe123";
        String password = "securePass";
        String email = "john.doe@example.com";
        String phoneNumber = "1234567890";
        LocalDate dateOfBirth = LocalDate.of(1995, 5, 15);
        String idNumber = "9505151234089";
        Boolean login = true;

        BasicUser basicUser = BasicUserFactory.create(
                firstName, lastName, username, password, email, phoneNumber,
                dateOfBirth, idNumber, login
        );

        assertNotNull(basicUser, "BasicUser should not be null");
        assertEquals(firstName, basicUser.getFirstName());
        assertEquals(lastName, basicUser.getLastName());
        assertEquals(username, basicUser.getUsername());
        assertEquals(password, basicUser.getPassword());
        assertEquals(email, basicUser.getEmail());
        assertEquals(phoneNumber, basicUser.getPhoneNumber());
        assertEquals(dateOfBirth, basicUser.getDateOfBirth());
        assertEquals(idNumber, basicUser.getIdNumber());
        assertEquals(login, basicUser.isLogin());
        assertEquals(UserType.BASIC, basicUser.getUserType(), "UserType should always be BASIC");

        System.out.println(basicUser);
    }

}
