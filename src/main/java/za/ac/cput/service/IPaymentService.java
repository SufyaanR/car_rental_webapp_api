package za.ac.cput.service;

import za.ac.cput.domain.Payment;

public interface IPaymentService extends IService<Payment, Long> {

void processPayment(Payment payment);
    
}
