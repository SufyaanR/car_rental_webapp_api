package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

/**
 * Professional user entity extending base User
 * Author: Nompumezo Mcatshulwa (222614153)
 */
@Entity
@DiscriminatorValue("PRO_USER")
public class ProUser extends User {
    private String businessName;
    private String businessRegistrationNumber;

    protected ProUser() {} // JPA required

    private ProUser(Builder builder) {
        super(builder);
        this.businessName = builder.businessName;
        this.businessRegistrationNumber = builder.businessRegistrationNumber;
    }

    // Getters
    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    /**
     * Builder pattern implementation
     */
    public static class Builder extends User.Builder {
        private String businessName;
        private String businessRegistrationNumber;

        public Builder setBusinessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public Builder setBusinessRegistrationNumber(String regNumber) {
            this.businessRegistrationNumber = regNumber;
            return this;
        }

        @Override
        public ProUser build() {
            return new ProUser(this);
        }

        @Override
        public Builder copy(User user) {
            super.copy(user);
            if (user instanceof ProUser) {
                ProUser proUser = (ProUser) user;
                this.businessName = proUser.businessName;
                this.businessRegistrationNumber = proUser.businessRegistrationNumber;
            }
            return this;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "ProUser{userId=%d, businessName='%s', registration='%s'}",
                getUserId(), businessName, businessRegistrationNumber
        );
    }
}