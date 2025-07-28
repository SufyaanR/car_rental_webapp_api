package za.ac.cput.util;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.apache.commons.validator.routines.EmailValidator;

public class Helper {
    private static EmailValidator emailValidator = EmailValidator.getInstance();
    private static PhoneNumberUtil phoneNumberValidator = PhoneNumberUtil.getInstance();

    //Validate phone number
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (isNullOrEmpty(phoneNumber)) {
            return false;
        }
        try {
            Phonenumber.PhoneNumber number = phoneNumberValidator.parse(phoneNumber, "RSA");
            return phoneNumberValidator.isValidNumber(number);
        } catch (Exception e) {
            return false;
        }}

    //Validate email
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        return emailValidator.isValid(email);
    }

    //Validate if string is null or empty
    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }
}
