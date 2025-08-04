package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

/**
 * ProUser.java
 * Professional user entity (no unique fields, inherits from User)
 * Author: Nompumezo Mcatshulwa (222614153)
 */
@Entity
@DiscriminatorValue("PRO_USER")
public class ProUser extends User {

    protected ProUser() {
        // JPA required no-arg constructor
    }

    private ProUser(Builder builder) {
        super(builder);
    }

    public static class Builder extends User.Builder {
        @Override
        public ProUser build() {
            return new ProUser(this);
        }

        @Override
        public Builder copy(User user) {
            super.copy(user);
            return this;
        }
    }

    @Override
    public String toString() {
        return "ProUser{" + super.toString() + "}";
    }
}