package za.ac.cput.service;

import za.ac.cput.domain.*;
import za.ac.cput.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Transactional
public class SubscriptionServiceImpl implements ISubscriptionService {

   private final SubscriptionRepo subscriptionPaymentRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepo subscriptionPaymentRepository) {
        this.subscriptionPaymentRepository = subscriptionPaymentRepository;
    }

    @Override
    public SubscriptionPayment save(SubscriptionPayment payment) {
        long recipientCount = Stream.of(payment.getProUser(), payment.getBusinessUser())
                .filter(Objects::nonNull)
                .count();
        if (recipientCount != 1) {
            throw new IllegalArgumentException("SubscriptionPayment must have exactly one recipient (ProUser or BusinessUser)");
        }
        return subscriptionPaymentRepository.save(payment);
    }

    @Override
    public SubscriptionPayment findById(Long id) {
        return subscriptionPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubscriptionPayment not found"));
    }

    @Override
    public List<SubscriptionPayment> findAll() {
        return subscriptionPaymentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        subscriptionPaymentRepository.deleteById(id);
    }

    public SubscriptionPayment createForProUser(ProUser proUser, SubscriptionPayment payment) {
        payment = new SubscriptionPayment.Builder()
                .setAmount(payment.getAmount())
                .setPaymentDate(payment.getPaymentDate())
                .setPaymentTime(payment.getPaymentTime())
                .setProUser(proUser)
                .build();
        return save(payment);
    }

    public SubscriptionPayment createForBusinessUser(BusinessUser businessUser, SubscriptionPayment payment) {
        payment = new SubscriptionPayment.Builder()
                .setAmount(payment.getAmount())
                .setPaymentDate(payment.getPaymentDate())
                .setPaymentTime(payment.getPaymentTime())
                .setBusinessUser(businessUser)
                .build();
        return save(payment);
    }

    public SubscriptionPayment update(Long id, SubscriptionPayment updatedPayment) {
        SubscriptionPayment existingPayment = findById(id);

        SubscriptionPayment.Builder builder = new SubscriptionPayment.Builder()
                .setSubscriptionPaymentId(existingPayment.getSubscriptionPaymentId())
                .setAmount(updatedPayment.getAmount() != null ? updatedPayment.getAmount() : existingPayment.getAmount())
                .setPaymentDate(updatedPayment.getPaymentDate() != null ? updatedPayment.getPaymentDate() : existingPayment.getPaymentDate())
                .setPaymentTime(updatedPayment.getPaymentTime() != null ? updatedPayment.getPaymentTime() : existingPayment.getPaymentTime())
                .setProUser(updatedPayment.getProUser() != null ? updatedPayment.getProUser() : existingPayment.getProUser())
                .setBusinessUser(updatedPayment.getBusinessUser() != null ? updatedPayment.getBusinessUser() : existingPayment.getBusinessUser());

        SubscriptionPayment payment = builder.build();
        return save(payment);
    }
}
