package za.ac.cput.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import za.ac.cput.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

@Modifying
@Transactional
@Query("""
    DELETE FROM Booking b 
    WHERE b.user.userId = :userId
       OR b.car.proUser.userId = :userId
       OR b.car.businessUser.userId = :userId
""")
void deleteAllByUserId(@Param("userId") Long userId);

    List<Booking> findByCarProUserUserId(Long proUserId);

    List<Booking> findByCarBusinessUserUserId(Long businessUserId);

}
