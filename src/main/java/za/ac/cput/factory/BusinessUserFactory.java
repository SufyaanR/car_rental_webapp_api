package za.ac.cput.factory;
import java.time.LocalDate;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.UserType;

public class BusinessUserFactory {

    public static BusinessUser createBusinessUser(
            String firstName,
            String lastName,
            LocalDate dateOfBirth,
            String idNumber,
            String email,
            String phoneNumber,
            UserType userType,
            String username,
            String password,
            boolean login,
            String bankName,
            String accountHolder,
            int accountNumber,
            String accountType,
            String businessName,
            String businessRegistrationNumber) {
        return new BusinessUser.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setDateOfBirth(dateOfBirth)
                .setIdNumber(idNumber)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUserType(userType)
                .setUsername(username)
                .setPassword(password)
                .setLogin(login)
                .setBankName(bankName)
                .setAccountHolder(accountHolder)
                .setAccountNumber(accountNumber)
                .setAccountType(accountType)
                .setBusinessName(businessName)
                .setBusinessRegistrationNumber(businessRegistrationNumber)
                .build();
    }
}
