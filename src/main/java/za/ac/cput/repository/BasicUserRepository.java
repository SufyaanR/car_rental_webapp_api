package za.ac.cput.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.cput.domain.BasicUser;

@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {

    Optional<BasicUser> findByUsername(String username);

}

