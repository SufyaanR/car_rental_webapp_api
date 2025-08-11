package za.ac.cput.repository;

import za.ac.cput.domain.PaymentStatus;
import java.util.Set;

/**
 * IPaymentStatusRepository.java
 * Repository interface for PaymentStatus
 * Author: 222614153
 */
public interface IPaymentStatusRepository {
    Set<PaymentStatus> getAll();
    PaymentStatus findByName(String name);
}