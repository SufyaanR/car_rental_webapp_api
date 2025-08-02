package za.ac.cput.service;

import za.ac.cput.domain.Payment;
import java.util.*;

public interface IPaymentService {

    Payment save(Payment payment);
    Payment read(Long paymentId);
    void delete(Long paymentId);
    List<Payment> findall();
    
}
