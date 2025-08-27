package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class RentalProvider extends User {

    private String bankName;
    private String accountHolder;
    private int accountNumber;
    private String accountType;

    protected RentalProvider() {
        super();
    }

    protected RentalProvider(Long userId, String firstName, String lastName, LocalDate dateOfBirth, String idNumber,
                             String email, String phoneNumber, UserType userType, String username,
                             String password, boolean login,
                             String bankName, String accountHolder, int accountNumber, String accountType) {
        super(userId, firstName, lastName, dateOfBirth, idNumber, email, phoneNumber, userType, username, password, login);
        this.bankName = bankName;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
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

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
