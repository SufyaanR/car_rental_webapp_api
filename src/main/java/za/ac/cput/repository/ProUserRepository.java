package za.ac.cput.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ProUser;

@Repository
public interface ProUserRepository extends JpaRepository<ProUser, Long> {

    Optional<ProUser> findByUsername(String username);
    
}