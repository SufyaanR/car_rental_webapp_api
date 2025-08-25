package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
public class BasicUser extends User {

    @OneToOne(cascade = CascadeType.ALL)
    private Booking bookCar;

    protected BasicUser() {
    }

    private BasicUser(Builder builder) {
        super(builder.userId, builder.firstName, builder.lastName, builder.dateOfBirth,
                builder.idNumber, builder.email, builder.phoneNumber,
                builder.userType, builder.username, builder.password, builder.login);
        this.bookCar = builder.bookCar;
    }

    @Override
    public String toString() {
        return "BasicUser{" + super.toString() + "}";
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
        private Boolean login;
        private Booking bookCar;

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

        public Builder setLogin(Boolean login) {
            this.login = login;
            return this;
        }

        public Builder setBookCar(Booking bookCar) {
            this.bookCar = bookCar;
            return this;
        }

        public BasicUser build() {
            return new BasicUser(this);
        }
    }
}