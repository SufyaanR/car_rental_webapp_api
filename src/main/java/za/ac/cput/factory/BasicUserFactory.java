package za.ac.cput.factory;

import za.ac.cput.domain.BasicUser;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class BasicUserFactory {
    public static BasicUser createBasicUser(

            String firstName,
            String lastName,
            String email,
            LocalDate dateOfBirth,
            String idNumber,
            String phoneNumber,
            String userName,
            String password
    ) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(email)
                || Helper.isNullOrEmpty(idNumber) || Helper.isNullOrEmpty(phoneNumber)
                || Helper.isNullOrEmpty(userName) || Helper.isNullOrEmpty(password) || dateOfBirth == null) {
            throw new IllegalArgumentException("One or more required fields are null or empty.");
        }

        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        if (!Helper.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number.");
        }

        return new BasicUser.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setDateOfBirth(dateOfBirth)
                .setIdNumber(idNumber)
                .setPhoneNumber(phoneNumber)
                .setUserName(userName)
                .setPassword(password)
                .build();
    }

}

