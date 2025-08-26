package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BusinessUser extends RentalProvider {

    private String businessName;
    private String businessRegistrationNumber;

    @OneToMany(mappedBy = "businessUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    @OneToMany(mappedBy = "businessUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubscriptionPayment> subscriptionPayments = new ArrayList<>();

    protected BusinessUser() {
        super();
    }

    private BusinessUser(Builder builder) {
        super(builder.userId, builder.firstName, builder.lastName, builder.dateOfBirth, builder.idNumber,
              builder.email, builder.phoneNumber, builder.userType, builder.username,
              builder.password, builder.login, builder.bankName, builder.accountHolder,
              builder.accountNumber, builder.accountType);

        this.businessName = builder.businessName;
        this.businessRegistrationNumber = builder.businessRegistrationNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<SubscriptionPayment> getSubscriptionPayments() {
        return subscriptionPayments;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setSubscriptionPayments(List<SubscriptionPayment> subscriptionPayments) {
        this.subscriptionPayments = subscriptionPayments;
    }

    @Override
public String toString() {
    return super.toString() + " BusinessUser{" +
            "businessName='" + businessName + '\'' +
            ", businessRegistrationNumber='" + businessRegistrationNumber + '\'' +
            ", carsCount=" + (cars != null ? cars.size() : 0) +
            ", subscriptionPaymentsCount=" + (subscriptionPayments != null ? subscriptionPayments.size() : 0) +
            '}';
}

    public static class Builder {
        private Long userId;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String idNumber;
        private String email;
        private String phoneNumber;
        private UserType userType;
        private String username;
        private String password;
        private boolean login;
        private String bankName;
        private String accountHolder;
        private int accountNumber;
        private String accountType;
        private String businessName;
        private String businessRegistrationNumber;

        public Builder setUserId(Long userId){
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

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setIdNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setUserType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setLogin(boolean login) {
            this.login = login;
            return this;
        }

        public Builder setBankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        public Builder setAccountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
            return this;
        }

        public Builder setAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setAccountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder setBusinessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public Builder setBusinessRegistrationNumber(String businessRegistrationNumber) {
            this.businessRegistrationNumber = businessRegistrationNumber;
            return this;
        }

        public BusinessUser build() {
            return new BusinessUser(this);
        }
    }
}
