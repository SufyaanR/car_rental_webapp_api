package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    void updateCarAvailability(Long carId, boolean isAvailable);
}
