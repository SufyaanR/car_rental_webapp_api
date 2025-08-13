package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ProUser;

@Repository
public interface ProUserRepository extends JpaRepository<ProUser, String> {
    // Inherits all CRUD methods:
    // save(), findById(), deleteById(), findAll() etc.
}