package za.ac.cput.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import za.ac.cput.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

@Modifying
@Transactional
@Query("""
    DELETE FROM Payment p
    WHERE p.user.userId = :userId 
       OR p.booking.user.userId = :userId
       OR p.booking.car.proUser.userId = :userId
       OR p.booking.car.businessUser.userId = :userId
""")
void deleteAllByUserId(@Param("userId") Long userId);


    List<Payment> findByUserUserId(Long userId);

    List<Payment> findByProUserUserId(Long userId);

    List<Payment> findByBusinessUserUserId(Long userId);
}
    

