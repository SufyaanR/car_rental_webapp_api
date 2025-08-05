package za.ac.cput.domain;

import jakarta.persistence.Entity;

@Entity
public class BusinessUser extends RentalProvider {

    private String businessName;
    private String businessRegistrationNumber;

    protected BusinessUser(){
        super();
    }
    private BusinessUser(Builder builder) {
        this.businessName = builder.businessName;
        this.businessRegistrationNumber = builder.businessRegistrationNumber;
        this.rentalProviderId = builder.rentalProviderId;
        this.bankName = builder.bankName;
        this.accountHolder = builder.accountHolder;
        this.accountNumber = builder.accountNumber;
        this.accountType = builder.accountType;
    }
public String getBusinessName() {
        return businessName;
}
public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
}

    @Override
    public String toString() {
        return "BusinessUser{" +
                "businessName='" + businessName + '\'' +
                ", businessRegistrationNumber='" + businessRegistrationNumber + '\'' +
                ", rentalProviderId=" + rentalProviderId +
                ", bankName='" + bankName + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    public static final class Builder{
        private String businessName;
        private String businessRegistrationNumber;
        private long rentalProviderId;
        private String bankName;
        private String accountHolder;
        private int accountNumber;
        private String accountType;

        public Builder businessName(String businessName) {
            this.businessName = businessName;
            return this;
        }
        public Builder businessRegistrationNumber(String businessRegistrationNumber) {
            this.businessRegistrationNumber = businessRegistrationNumber;
            return this;
        }
        public Builder rentalProviderId(long rentalProviderId) {
            this.rentalProviderId = rentalProviderId;
            return this;
        }
        public Builder bankName(String bankName) {
            this.bankName = bankName;
            return this;
        }
        public Builder accountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
            return this;
        }
        public Builder accountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }
        public Builder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }
public Builder copy(BusinessUser businessUser) {
    this.businessName = businessUser.getBusinessName();
    this.businessRegistrationNumber = businessUser.getBusinessRegistrationNumber();
    this.rentalProviderId = businessUser.getRentalProviderId();
    this.bankName = businessUser.getBankName();
    this.accountHolder = businessUser.getAccountHolder();
    this.accountNumber = businessUser.getAccountNumber();
    this.accountType = businessUser.getAccountType();
    return this;
}
        public BusinessUser build(){
            return new BusinessUser(this);
        }

    }
}
