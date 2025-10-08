package za.ac.cput.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserUserId(Long userId);

    List<Payment> findByProUserUserId(Long userId);

    List<Payment> findByBusinessUserUserId(Long userId);
}
    

