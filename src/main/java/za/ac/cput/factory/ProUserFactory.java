package za.ac.cput.factory;

import java.time.LocalDate;
import za.ac.cput.domain.ProUser;
import za.ac.cput.domain.UserType;

public class ProUserFactory {

    public static ProUser create(String firstName, String lastName, String username,
                                 String password, String email, String phoneNumber,
                                 LocalDate dateOfBirth, String idNumber, boolean login,
                                 String bankName, String accountHolder,
                                 int accountNumber, String accountType) {
                                    
        return new ProUser.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setDateOfBirth(dateOfBirth)
                .setIdNumber(idNumber)
                .setLogin(login)
                .setUserType(UserType.PRO)
                .setBankName(bankName)
                .setAccountHolder(accountHolder)
                .setAccountNumber(accountNumber)
                .setAccountType(accountType)
                .build();
    }
}