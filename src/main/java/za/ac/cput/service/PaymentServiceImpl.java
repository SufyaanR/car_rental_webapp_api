package za.ac.cput.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository paymentrepo;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentrepo) {
        this.paymentrepo = paymentrepo;
        }

    @Override
    public Payment save(Payment payment) {
        return paymentrepo.save(payment);
    }

    @Override
    public Payment read(Long paymentId) {
        return paymentrepo.findById(paymentId).orElse(null);
    }

    @Override
    public void delete(Long paymentId) {
        paymentrepo.deleteById(paymentId);
    }

    @Override
    public List<Payment> findall() {
       return paymentrepo.findAll();
    }
    
}
