package za.ac.cput.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Payment;
import java.util.List;
import za.ac.cput.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository PaymentRepo;

    public PaymentServiceImpl(PaymentRepository PaymentRepo) {
        this.PaymentRepo = PaymentRepo;
    }

    @Override
    public Payment save(Payment payment) {
        return PaymentRepo.save(payment);
    }

    @Override
    public Payment findById(Long id) {
        return PaymentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public List<Payment> findAll() {
        return PaymentRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        PaymentRepo.deleteById(id);
    }

    public Payment update(Long id, Payment paymentUpdates) {
    Payment existingPayment = findById(id);

    Payment updatedPayment = new Payment.Builder()
            .setPaymentId(existingPayment.getPaymentId())
            .setAmount(paymentUpdates.getAmount() != null ? paymentUpdates.getAmount() : existingPayment.getAmount())
            .setPaymentDate(paymentUpdates.getPaymentDate() != null ? paymentUpdates.getPaymentDate() : existingPayment.getPaymentDate())
            .setPaymentTime(paymentUpdates.getPaymentTime() != null ? paymentUpdates.getPaymentTime() : existingPayment.getPaymentTime())
            .setBooking(paymentUpdates.getBooking() != null ? paymentUpdates.getBooking() : existingPayment.getBooking())
            .setUser(paymentUpdates.getUser() != null ? paymentUpdates.getUser() : existingPayment.getUser())
            .setCardNumber(paymentUpdates.getCardNumber() != 0 ? paymentUpdates.getCardNumber() : existingPayment.getCardNumber())
            .setNameOfCardHolder(paymentUpdates.getNameOfCardHolder() != null ? paymentUpdates.getNameOfCardHolder() : existingPayment.getNameOfCardHolder())
            .setExpiryDate(paymentUpdates.getExpiryDate() != null ? paymentUpdates.getExpiryDate() : existingPayment.getExpiryDate())
            .setCcv(paymentUpdates.getCcv() != null ? paymentUpdates.getCcv() : existingPayment.getCcv())
            .setPaymentStatus(paymentUpdates.getPaymentStatus() != null ? paymentUpdates.getPaymentStatus() : existingPayment.getPaymentStatus())
            .setProUser(paymentUpdates.getProUser() != null ? paymentUpdates.getProUser() : existingPayment.getProUser())
            .setBusinessUser(paymentUpdates.getBusinessUser() != null ? paymentUpdates.getBusinessUser() : existingPayment.getBusinessUser())
            .build();

    return save(updatedPayment);
}

    @Override
    public void processPayment(Payment payment) {
        payment.processPayment();
        PaymentRepo.save(payment);
    }
}