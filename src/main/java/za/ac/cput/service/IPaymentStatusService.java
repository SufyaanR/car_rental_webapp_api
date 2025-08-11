package za.ac.cput.service;

import za.ac.cput.domain.PaymentStatus;
import java.util.List;

public interface IPaymentStatusService {
    List<PaymentStatus> getAll();
    PaymentStatus findByName(String name);
}