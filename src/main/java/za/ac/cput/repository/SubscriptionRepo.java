package za.ac.cput.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.SubscriptionPayment;

@Repository
public interface SubscriptionRepo extends JpaRepository<SubscriptionPayment, UUID> {
    
}
