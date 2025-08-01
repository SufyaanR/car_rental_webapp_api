package za.ac.cput.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.SubscriptionPayment;
import za.ac.cput.repository.SubscriptionRepo;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

    private final SubscriptionRepo subscriptionRepo;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
        }

    @Override
    public SubscriptionPayment save(SubscriptionPayment subscriptionPayment) {
        return subscriptionRepo.save(subscriptionPayment);
    }

    @Override
    public SubscriptionPayment read(UUID subscriptionPaymentId) {
        return subscriptionRepo.findById(subscriptionPaymentId).orElse(null);
    }

    @Override
    public void delete(UUID subscriptionPaymentId) {
        subscriptionRepo.deleteById(subscriptionPaymentId);
    }

    @Override
    public List<SubscriptionPayment> findall() {
        return subscriptionRepo.findAll();
    }
    
}
