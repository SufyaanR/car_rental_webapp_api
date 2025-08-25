package za.ac.cput.factory;

import java.time.LocalDate;
import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.UserType;

public class BasicUserFactory {

    public static BasicUser create(String firstName, String lastName, String username,
                                   String password, String email, String phoneNumber,
                                   LocalDate dateOfBirth, String idNumber, Boolean login) {
        return new BasicUser.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setDateOfBirth(dateOfBirth)
                .setIdNumber(idNumber)
                .setLogin(login)
                .setUserType(UserType.BASIC)
                .build();
    }
}