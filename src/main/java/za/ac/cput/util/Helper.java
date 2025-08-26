package za.ac.cput.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.apache.commons.validator.routines.EmailValidator;

public class Helper {
    private static EmailValidator emailValidator = EmailValidator.getInstance();
    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    //Validate phone number
   public static boolean isValidPhoneNumber(String phoneNumber, String regionCode) {
        try {
            Phonenumber.PhoneNumber number = phoneUtil.parse(phoneNumber, regionCode);
            return phoneUtil.isValidNumber(number);
        } catch (NumberParseException e) {
            return false;
        }
    }
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
