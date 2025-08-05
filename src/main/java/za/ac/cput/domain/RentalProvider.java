package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RentalProvider {

    @Id
    protected long rentalProviderId;
    protected  String bankName;
    protected  String accountHolder;
    protected  int accountNumber;
    protected String accountType;

    protected RentalProvider() {
    }
    private RentalProvider(Builder builder) {
        this.rentalProviderId = builder.rentalProviderId;
        this.bankName = builder.bankName;
        this.accountHolder = builder.accountHolder;
        this.accountNumber = builder.accountNumber;
        this.accountType = builder.accountType;
    }
    public long getRentalProviderId() {
        return rentalProviderId;
    }
    public String getBankName() {
        return bankName;
    }
    public String getAccountHolder() {
        return accountHolder;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "RentalProvider{" +
                "rentalProviderId=" + rentalProviderId +
                ", bankName='" + bankName + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    public static class Builder {
        protected long rentalProviderId;
        protected String bankName;
        protected String accountHolder;
        protected int accountNumber;
        protected String accountType;

public Builder setRentalProviderID(long rentalProviderId) {
this.rentalProviderId = rentalProviderId;
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
        public Builder copy(RentalProvider rentalProvider){
    this.rentalProviderId = rentalProvider.rentalProviderId;
    this.bankName = rentalProvider.bankName;
    this.accountHolder = rentalProvider.accountHolder;
    this.accountNumber = rentalProvider.accountNumber;
    this.accountType = rentalProvider.accountType;
    return this;
        }
public RentalProvider build() {
    return new RentalProvider(this);
}
    }
}
