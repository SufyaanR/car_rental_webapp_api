package za.ac.cput.repository;

import za.ac.cput.domain.ProUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProUserRepository extends JpaRepository<ProUser, String> {
}