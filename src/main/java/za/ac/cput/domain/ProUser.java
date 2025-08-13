package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class ProUser {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    protected ProUser() {} // For JPA

    private ProUser(Builder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProUser)) return false;
        ProUser proUser = (ProUser) o;
        return Objects.equals(userId, proUser.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "ProUser{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder {
        private String userId;
        private String firstName;
        private String lastName;
        private String email;

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ProUser build() {
            return new ProUser(this);
        }
    }
}