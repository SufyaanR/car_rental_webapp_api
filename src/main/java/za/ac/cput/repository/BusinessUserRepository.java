package za.ac.cput.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.BusinessUser;

public interface BusinessUserRepository extends JpaRepository<BusinessUser, Long>{
    
    Optional<BusinessUser> findByUsername(String username);
    
}
