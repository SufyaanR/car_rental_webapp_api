package za.ac.cput.repository;

import za.ac.cput.domain.PaymentStatus;

import java.util.HashSet;
import java.util.Set;

/**
 * PaymentStatusRepository.java
 * Repository for PaymentStatus enum values
 * Author: 222614153
 */
public class PaymentStatusRepository implements IPaymentStatusRepository {
    private static PaymentStatusRepository repository = null;
    private final Set<PaymentStatus> paymentStatusSet;

    private PaymentStatusRepository() {
        paymentStatusSet = new HashSet<>();
        // Initialize with enum values
        paymentStatusSet.add(PaymentStatus.PENDING);
        paymentStatusSet.add(PaymentStatus.SUCCESSFUL);
        paymentStatusSet.add(PaymentStatus.FAILED);
    }

    public static PaymentStatusRepository getRepository() {
        if (repository == null) {
            repository = new PaymentStatusRepository();
        }
        return repository;
    }

    @Override
    public Set<PaymentStatus> getAll() {
        return paymentStatusSet;
    }

    @Override
    public PaymentStatus findByName(String name) {
        return paymentStatusSet.stream()
                .filter(status -> status.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}