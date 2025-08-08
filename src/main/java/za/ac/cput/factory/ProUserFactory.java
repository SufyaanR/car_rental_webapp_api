package za.ac.cput.factory;

import za.ac.cput.domain.ProUser;
import za.ac.cput.util.Helper;

/**
 * ProUserFactory.java
 * Factory for ProUser entity
 * Author: 222614153
 */
public class ProUserFactory {
    public static ProUser buildProUser(String firstName, String lastName, String email) {
        if (Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(email)) {
            throw new IllegalArgumentException("All required fields must be provided");
        }

        return new ProUser.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build();
    }
}