package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

}
