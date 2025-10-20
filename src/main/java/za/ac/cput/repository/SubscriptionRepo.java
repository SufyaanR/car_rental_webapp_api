package za.ac.cput.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import za.ac.cput.domain.SubscriptionPayment;

@Repository
public interface SubscriptionRepo extends JpaRepository<SubscriptionPayment, Long> {

    @Modifying
    @Transactional
    @Query("""
                DELETE FROM SubscriptionPayment s
                WHERE (s.proUser.userId = :userId OR s.businessUser.userId = :userId)
            """)
    void deleteAllByUserId(@Param("userId") Long userId);

    List<SubscriptionPayment> findByProUserUserId(Long userId);

    List<SubscriptionPayment> findByBusinessUserUserId(Long userId);
}
