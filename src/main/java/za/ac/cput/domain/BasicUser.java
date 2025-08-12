package za.ac.cput.domain;

import jakarta.persistence.Entity;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import za.ac.cput.domain.Car;

@Entity
public class BasicUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    protected  String firstName;
    protected String lastName;
    protected String email;
    protected LocalDate dateOfBirth;
    protected String idNumber;
    protected String phoneNumber;
    protected String userName;
    protected String password;
    protected UserType profileType = UserType.BASIC;


    public BasicUser() {

    }

    public BasicUser(Builder builder) {
        this.idNumber = builder.idNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.dateOfBirth = builder.dateOfBirth;
        this.idNumber = builder.idNumber;
        this.phoneNumber = builder.phoneNumber;
        this.userName = builder.userName;
        this.profileType = UserType.BASIC;
        this.password= (builder.password);
    }

    public String bookCar(Car car, LocalDate startDate, LocalDate endDate) {
        if (car == null) {
            return "Booking failed: Car is not available.";
        }
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            return "Booking failed: Invalid rental dates.";
        }

        // Simulate a booking confirmation (in a real app, you'd call a BookingService and persist to DB)
        String confirmation = "Booking confirmed for " + car.getModel() + " " + car.getModel() +
                " from " + startDate + " to " + endDate + ".";
        System.out.println(confirmation);
        return confirmation;
    }

    @Override
    public String toString() {
        return "BasicUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", idNumber='" + idNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate dateOfBirth;
        private String idNumber;
        private String phoneNumber;
        private String userName;
        private UserType profileType = UserType.BASIC;
        private String password;

        public Builder setId(Long id) {
            this.id = id;
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

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setIdNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setProfileType(UserType profileType) {
            this.profileType = profileType;
            return this;
        }



        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public BasicUser build() {
            return new BasicUser(this);
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }
    }
}
