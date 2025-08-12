package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.BasicUser;

public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {

    BasicUser findByEmail(String email);
}
