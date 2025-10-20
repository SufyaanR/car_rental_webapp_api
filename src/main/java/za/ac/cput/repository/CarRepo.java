package za.ac.cput.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import za.ac.cput.domain.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    @Modifying
    @Transactional
    @Query("""
                DELETE FROM Car c
                WHERE c.proUser.userId = :userId
                   OR c.businessUser.userId = :userId
            """)
    void deleteAllByUserId(@Param("userId") Long userId);

    List<Car> findAllByProUserUserId(Long proUserId);

    List<Car> findAllByBusinessUserUserId(Long businessUserId);

}
