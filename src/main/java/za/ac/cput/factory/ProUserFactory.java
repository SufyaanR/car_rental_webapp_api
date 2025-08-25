package za.ac.cput.factory;

import za.ac.cput.domain.ProUser;
import za.ac.cput.util.Helper;

public class ProUserFactory {
    public static ProUser createProUser(String userId, String firstName,
                                        String lastName, String email) {

        if (Helper.isNullOrEmpty(userId) ||
                Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                !Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid user data");
        }

        return new ProUser.Builder()
                .setUserId(userId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build();
    }
}