package za.ac.cput.factory;

import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.util.Helper;

public class PaymentStatusFactory {
    public static PaymentStatus createStatus(String statusName) {
        if (Helper.isNullOrEmpty(statusName)) {
            throw new IllegalArgumentException("Status name is required");
        }

        try {
            return PaymentStatus.valueOf(statusName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid payment status: " + statusName);
        }
    }
}